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
import com.weida.domain.JsonData;
import com.weida.pojo.Agent;
import com.weida.pojo.AgentTariff;
import com.weida.pojo.Channel;
import com.weida.pojo.ChannelTariff;
import com.weida.service.AgentService;
import com.weida.service.AgentTariffService;
import com.weida.service.ChannelService;
import com.weida.service.ChannelTariffService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/21
 * Time:14:07
 */
@RestController
public class AgentTariffController extends BaseController {

    @Autowired
    AgentTariffService agentTariffService;

    @Autowired
    AgentService agentService;

    @Autowired
    ChannelTariffService channelTariffService;

    @Autowired
    ChannelService channelService;

    @PostMapping("/api/v1/agentTariff_add")
    public JsonData addAgentTariff(String packageId, @RequestParam(value = "agentName[]",required = false) String[] agentName, String channelName, String discount, HttpServletRequest request) {
//        log.info(packageId + "-" + agentName + "-" + channelName + "-" + discount);
        if (StringUtils.isBlank(packageId) || StringUtils.isBlank(channelName)) {
            return JsonData.buildFail("套餐包或者通道都是必须选择的", null);
        }
        if (!channelTariffService.channelExistPackageId(channelName, packageId)) {
            return JsonData.buildFail("该通道不含这个套餐包编码 请检查", null);
        }
        if (!NumberUtils.isParsable(discount)) {
            return JsonData.buildFail("折扣应该为数字", null);
        }
        double _discount = NumberUtils.toDouble(discount, 100.00D);
        if (_discount > 100 || _discount < 0){
            return JsonData.buildFail("折扣大于1或小于0， 不合理", null);
        }
        ChannelTariff channelTariff = channelTariffService.findByPackageId(packageId);
        Channel channel = channelService.getByChannelName(channelName);
        double price = (channelTariff.getOriginalPrice() * _discount) / 100;
        channelTariff.setId(null);
        String msg = "";
        if (agentName == null || agentName.length == 0) {
            List<String> agentNames = agentService.findAllAgentName();
            saveAgentTariff(agentNames,channel,channelTariff, price, request);
            msg = "成功添加" + agentNames.size() + "条数据";
            return JsonData.buildSuccess(msg, null);
        }
        if (agentName != null && agentName.length > 0){
            List<String> agentNames = new ArrayList<>(Arrays.asList(agentName));
            saveAgentTariff(agentNames,channel,channelTariff, price,request);
            msg = "成功添加" + agentNames.size() + "条数据";
            return JsonData.buildSuccess(msg, null);
        }
        return JsonData.buildSuccess(msg, null);
    }

    private void saveAgentTariff(List<String> agentNames, Channel channel, ChannelTariff channelTariff, double price, HttpServletRequest request){
        AgentTariff agentTariff = null;
        Agent agent = null;
        for (String _agentName : agentNames) {
            agent = agentService.findByName(_agentName);
            if (null == agent)
                continue;
            agentTariff = new AgentTariff();
            BeanUtils.copyProperties(channelTariff, agentTariff);
            agentTariff.setAgentName(_agentName);
            agentTariff.setTariffPrice(price);
            agentTariff.setRebateMode(agent.getRebateMode());
            agentTariff.setTotalStatus(agentTariffService.AgentTotalStatus(channel.getStatus(), agent.getStatus(), channelTariff.getStatus()));
            showLog(request, "添加了代理商资费计划", agentTariff);
            logUtil.addSystemUserLog(request, "添加了代理商资费计划", "增加");
            agentTariffService.addAgentTariff(agentTariff);
        }
    }

    @GetMapping("/api/v1/agentTariff_list")
    public PageInfo<AgentTariff> findAgentTariffList(int page, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode, Integer superpositionMode){
        return agentTariffService.findAgentTariffList(page, 20, operator, channelName, showName, agentName, packageId, status, feeMode, superpositionMode);
    }

    /**
     * 更新选中的价格
     * @param ids 主键ids
     * @param prices 价格数组
     * @return json字符串
     */
    @PostMapping("/api/v1/update_agentTariff_price")
    public JsonData updateAgentTariff(HttpServletRequest request, @RequestParam(value = "ids[]", required = false) Integer[] ids, @RequestParam(value = "prices[]", required = false) Double[] prices){
        if (ids == null || prices == null)
            return JsonData.buildFail("未选中任何数据", null);
        for (int i = 0; i < ids.length; i++) {
            agentTariffService.updatePrice(ids[i], prices[i]);
            showLog(request, "更改了代理商资费计划", ids[i]);
            logUtil.addSystemUserLog(request, "更改了代理商资费计划", "修改");
        }
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/delete_agentTariff")
    public JsonData deleteAgentTariff( HttpServletRequest request, @RequestParam(value = "ids[]", required = false) Integer[] ids){
        if (ids == null)
            return JsonData.buildFail("未选中任何数据", null);

        for (int i = 0; i < ids.length; i++) {
            agentTariffService.deleteById(ids[i]);
            showLog(request, "删除代理商资费计划", ids[i]);
            logUtil.addSystemUserLog(request, "删除了代理商资费计划", "删除");
        }
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/agentTariff_totalStatus")
    public JsonData reloadTotalStatus(){
       agentTariffService.updateAllTotalStatus();
        return JsonData.buildSuccess();
    }

}
