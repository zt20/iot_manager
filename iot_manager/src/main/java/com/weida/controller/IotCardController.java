/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weida.controller;

import com.github.pagehelper.PageInfo;
import com.monitorjbl.xlsx.StreamingReader;
import com.weida.domain.JsonData;
import com.weida.pojo.Channel;
import com.weida.pojo.IotCard;
import com.weida.service.ChannelService;
import com.weida.service.IotCardService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/26
 * Time:11:06
 */
@RestController
public class IotCardController extends BaseController {

    @Autowired
    ChannelService channelService;

    @Autowired
    IotCardService iotCardService;


    @PostMapping("/api/v1/IotCardByExcel_add")
    public JsonData importIotCard(String channelName, @RequestParam(required = false, value = "cardFile") MultipartFile cardFile, HttpServletRequest request) throws Exception {

        if (cardFile == null) {
            return JsonData.buildFail("请选择excel文件", null);
        }
        if (!cardFile.getOriginalFilename().endsWith("xlsx")) {
            return JsonData.buildFail("请上传.xlsx结尾excel文件", null);
        }
        if (StringUtils.isBlank(channelName)) {
            return JsonData.buildFail("请选择通道！", null);
        }
        List<IotCard> iotCards = new ArrayList<>();
        Channel channel = channelService.getByChannelName(channelName);
        InputStream inputStream = null;
        try {
            inputStream = cardFile.getInputStream();
            Workbook wb = StreamingReader.builder().rowCacheSize(500).bufferSize(1024).open(inputStream);//这个用的github上的一个开源项目可以加缓存 避免oom
            Sheet sheet = wb.getSheetAt(0);
            IotCard iotCard;
            String cell1;
            String cell2;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    if (!"卡号".equals(row.getCell(0).getStringCellValue()))
                        return JsonData.buildFail("第一列表头为卡号", null);
                    continue;
                }
                cell1 = row.getCell(0).getStringCellValue();
                if (StringUtils.isBlank(cell1))
                    continue;
                iotCard = new IotCard();
                iotCard.setCardId(cell1);
                cell2 = row.getCell(1).getStringCellValue();
                iotCard.setCardId(cell1);
                iotCard.setOperator(this.getOperator(cell2, channel.getOperator()));
                iotCard.setChannelName(channelName);
                iotCards.add(iotCard);
            }
        } catch (IOException e) {
            log.error("上传文件出现" + e.getMessage());
            return JsonData.buildFail("上传出现异常", null);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("上传文件出现" + e.getMessage());
                return JsonData.buildFail("上传出现异常", null);
            }
        }
        iotCardService.addIotCards(iotCards);
        showLog(request, "上传excel添加物联网卡数据", cardFile.getOriginalFilename());
        logUtil.addSystemUserLog(request, "上传excel添加物联网卡数据", "添加");
        return JsonData.buildSuccess("上传成功！" + iotCards.size() + "条", null);
    }

    private int getOperator(String operatorStr, int operator) {
        if ("移动".equals(operatorStr))
            return 1;
        if ("联通".equals(operatorStr))
            return 2;
        if ("电信".equals(operatorStr))
            return 3;
        return operator;
    }

    @PostMapping("/api/v1/IotCardByExcel_delete")
    public JsonData deleteFromExcel(@RequestParam(value = "deleteCardFile", required = false) MultipartFile deleteCardFile, String channelName, HttpServletRequest request) {
        if (deleteCardFile == null || StringUtils.isBlank(channelName))
            return JsonData.buildFail("文件和通道都是必须选择！", null);
        if (!deleteCardFile.getOriginalFilename().endsWith("xlsx"))
            return JsonData.buildFail("请选择xlsx结尾的excel文件", null);

        List<String> iotCards = new ArrayList<>();
        InputStream inputStream = null;
        try {
            inputStream = deleteCardFile.getInputStream();
            Workbook wb = StreamingReader.builder().rowCacheSize(500).bufferSize(1024).open(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            String cell1;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    if (!"卡号".equals(row.getCell(0).getStringCellValue()))
                        return JsonData.buildFail("第一列表头为卡号", null);
                    continue;
                }
                cell1 = row.getCell(0).getStringCellValue();
                if (StringUtils.isBlank(cell1))
                    continue;
                iotCards.add(cell1);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return JsonData.buildFail("上传出现异常", null);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("上传文件出现" + e.getMessage());
                return JsonData.buildFail("上传出现异常", null);
            }

        }
        showLog(request, "上传excel删除物联网卡数据", deleteCardFile.getOriginalFilename());
        logUtil.addSystemUserLog(request, "上传excel删除物联卡数据", "删除");
        iotCardService.deleteByIotCards(iotCards);

        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/iotCard_list")
    public PageInfo<IotCard> findByChooseList(int page, String startCardId, String endCardId, String cardId, Integer operator,
                                              Integer status, String channelName, String agentName, Integer trafficPool, Integer networkStatus) {
        return iotCardService.findByChooseList(page, 20, startCardId, endCardId, cardId, operator, status, channelName, agentName, trafficPool, networkStatus);
    }


    @PostMapping("/api/v1/IotCardByExcel_updateUse")
    public JsonData updateUseFromExcel(String channelName, @RequestParam(value = "updateUseCardFile", required = false) MultipartFile updateUseCardFile, HttpServletRequest request) {

        if (StringUtils.isBlank(channelName) || updateUseCardFile == null) {
            return JsonData.buildFail("通道和文件都是必须选择的！", null);
        }

        if (!updateUseCardFile.getOriginalFilename().endsWith("xlsx")) {
            return JsonData.buildFail("请选择xlsx结尾的文件", null);
        }

        Map<String, List<String>> useTraffic = new HashMap<>();

        InputStream is = null;
        try {
            is = updateUseCardFile.getInputStream();
            Workbook wb = StreamingReader.builder().rowCacheSize(500).bufferSize(1024).open(is);
            Sheet sheet = wb.getSheetAt(0);
            String cell1;
            String cell2;
            String cell3;
            ArrayList<String> use_total;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    if (!"卡号".equals(row.getCell(0).getStringCellValue()))
                        return JsonData.buildFail("第一列表头为卡号", null);
                    continue;
                }
                cell1 = row.getCell(0).getStringCellValue();
                cell2 = row.getCell(1).getStringCellValue();
                cell3 = row.getCell(2).getStringCellValue();
                if (StringUtils.isBlank(cell1) || StringUtils.isBlank(cell2) || StringUtils.isBlank(cell3))
                    continue;
                use_total = new ArrayList<>();
                use_total.add(cell2);
                use_total.add(cell3);
                useTraffic.put(cell1, use_total);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return JsonData.buildFail("上传出现异常", null);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("上传文件出现" + e.getMessage());
                return JsonData.buildFail("上传出现异常", null);
            }

        }
        showLog(request, "上传excel更新用量", updateUseCardFile.getOriginalFilename());
        logUtil.addSystemUserLog(request, "上传excel更新用量", "更新");
        iotCardService.updateTrafficUse(useTraffic);
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/batch_setIotCard")
    public JsonData batchSetIotCard(HttpServletRequest request, String action, String HasSet, String SMSISDN, String EMSISDN, String MSISDN, Integer OperatorType, Integer Status, String agentAccount, String interfaceID, Integer setStatus, Integer setCutService, Integer setSyncMode, Integer setPoolMode) {
//        System.out.println(action + "->" + HasSet + "->" + SMSISDN + "->" + EMSISDN + "->" + MSISDN + "->" + OperatorType + "->" + Status + "->" + agentAccount + "->" + interfaceID);
        if (StringUtils.isBlank(SMSISDN) && StringUtils.isBlank(EMSISDN) && StringUtils.isBlank(MSISDN) && OperatorType == null && Status == null && StringUtils.isBlank(interfaceID)) {
            return JsonData.buildFail("未选中任何条件, 无法操作", null);
        }
        if ("setToAgentSeg".equals(action)) {
            List<IotCard> iotCards = iotCardService.findIotCards(SMSISDN, EMSISDN, MSISDN, OperatorType, Status, agentAccount, interfaceID);
            for (IotCard iotCard : iotCards) {
                if (StringUtils.isNotBlank(iotCard.getAgentName()))
                    continue;
                iotCardService.updateAgent(iotCard.getId(), agentAccount);
            }
        }
        if ("setStatusSeg".equals(action)) {
            List<IotCard> iotCards = iotCardService.findIotCards(SMSISDN, EMSISDN, MSISDN, OperatorType, Status, agentAccount, interfaceID);
            for (IotCard iotCard : iotCards) {
                iotCardService.updateStatus(iotCard.getId(), setStatus);
            }
        }
        if ("setCutServiceSeg".equals(action)){
            List<IotCard> iotCards = iotCardService.findIotCards(SMSISDN, EMSISDN, MSISDN, OperatorType, Status, agentAccount, interfaceID);
            for (IotCard iotCard : iotCards) {
                iotCardService.updateNetwork(iotCard.getId(), setCutService);
            }
        }
        if ("setSyncModeSeg".equals(action)){
            List<IotCard> iotCards = iotCardService.findIotCards(SMSISDN, EMSISDN, MSISDN, OperatorType, Status, agentAccount, interfaceID);
            for (IotCard iotCard : iotCards) {
                iotCardService.updateCardType(iotCard.getId(), setSyncMode);
            }
        }
        if ("setPoolModeSeg".equals(action)){
            List<IotCard> iotCards = iotCardService.findIotCards(SMSISDN, EMSISDN, MSISDN, OperatorType, Status, agentAccount, interfaceID);
            for (IotCard iotCard : iotCards) {
                iotCardService.updateTrafficPool(iotCard.getId(), setPoolMode);
            }
        }
        showLog(request, "批量修改物联网卡", action);
        logUtil.addSystemUserLog(request, "批量修改物联网卡", "修改");
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/checkAgentCount")
    public JsonData checkAgentCount(String action, String startCardId, String endCardId, String cardId, Integer operator,
                                    Integer status, String channelName) {
        if (StringUtils.isBlank(startCardId) && StringUtils.isBlank(endCardId) && StringUtils.isBlank(cardId) && operator == null && status == null && StringUtils.isBlank(channelName))
            return JsonData.buildSuccess("0", null);
        List<IotCard> iotCards = iotCardService.findIotCards(startCardId, endCardId, cardId, operator, status, null, channelName);
        if ("agent".equals(action)) {
            int size = 0;
            for (IotCard iotCard : iotCards) {
                if (StringUtils.isBlank(iotCard.getAgentName())) {
                    ++size;
                }
            }
            return JsonData.buildSuccess(String.valueOf(size), null);
        }
        if ("status".equals(action)) {
            int size;
            size = iotCards.size();
            return JsonData.buildSuccess(String.valueOf(size), null);
        }
        if ("network".equals(action)){
            int size;
            size = iotCards.size();
            return JsonData.buildSuccess(String.valueOf(size), null);
        }
        if ("cardType".equals(action)){
            int size;
            size = iotCards.size();
            return JsonData.buildSuccess(String.valueOf(size), null);
        }
        if ("trafficPool".equals(action)){
            int size;
            size = iotCards.size();
            return JsonData.buildSuccess(String.valueOf(size), null);
        }
        return JsonData.buildSuccess("0", null);
    }


}
