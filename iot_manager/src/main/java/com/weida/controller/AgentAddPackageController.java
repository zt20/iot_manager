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
import com.weida.pojo.AgentAddPackage;
import com.weida.pojo.Channel;
import com.weida.pojo.ChannelAddPackage;
import com.weida.service.AgentAddPackageService;
import com.weida.service.AgentService;
import com.weida.service.ChannelAddPackageService;
import com.weida.service.ChannelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/25
 * Time:11:04
 */
@RestController
public class AgentAddPackageController extends BaseController {

    @Autowired
    AgentAddPackageService agentAddPackageService;

    @Autowired
    ChannelAddPackageService channelAddPackageService;

    @Autowired
    ChannelService channelService;

    @Autowired
    AgentService agentService;

    /**
     * 更新选中的价格
     *
     * @param ids    主键ids
     * @param prices 价格数组
     * @return json字符串
     */
    @PostMapping("/api/v1/update_agentAddPackage_price")
    public JsonData updateAgentAddPackage(HttpServletRequest request, @RequestParam(value = "ids[]", required = false) Integer[] ids, @RequestParam(value = "prices[]", required = false) Double[] prices) {
        if (ids == null || prices == null)
            return JsonData.buildFail("未选中任何数据", null);
        for (int i = 0; i < ids.length; i++) {
            agentAddPackageService.updatePrice(ids[i], prices[i]);
            showLog(request, "更改了代理商加油包价格", ids[i]);
            logUtil.addSystemUserLog(request, "更改了代理商加油包价格", "修改");
        }
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/delete_agentAddPackage")
    public JsonData deleteAgentAddPackage(@RequestParam(value = "ids[]", required = false) Integer[] ids, HttpServletRequest request) {
        if (null == ids)
            return JsonData.buildFail("未选中任何数据", null);

        for (int i = 0; i < ids.length; i++) {
            agentAddPackageService.deleteById(ids[i]);
            showLog(request, "删除了代理商加油包", ids[i]);
            logUtil.addSystemUserLog(request, "删除代理商加油包", "删除");
        }
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/agentAddPackage_add")
    public JsonData addAgentAddPackage(HttpServletRequest request, @RequestParam(value = "agentName[]", required = false) String[] agentName, String channelName, String discount, String packageId) {

        if (StringUtils.isBlank(channelName) || StringUtils.isBlank(packageId))
            return JsonData.buildFail("通道或者折扣为必填项");
        if (!NumberUtils.isParsable(discount))
            return JsonData.buildFail("折扣应该为数字", null);
        double _discount = NumberUtils.toDouble(discount, 100.00D);
        if (_discount > 100 || _discount < 0) {
            return JsonData.buildFail("折扣大于1或小于0， 不合理", null);
        }

        ChannelAddPackage channelAddPackage = channelAddPackageService.findByPackageId(packageId);
        log.info("channelAddPackage-->" + channelAddPackage + "->" + _discount + "-->" + packageId);
        double price = (channelAddPackage.getOriginalPrice() * _discount) / 100;

        Channel channel = channelService.getByChannelName(channelName);
        channelAddPackage.setId(null);
        if (agentName == null || agentName.length <= 0) {
            List<String> agentNames = agentService.findAllAgentName();
            saveAddPackage(agentNames, channel, channelAddPackage, price, request);
        }

        log.info(agentName + "-->" + agentName.length);
        if (agentName != null && agentName.length > 0) {
            List<String> agentNames = Arrays.asList(agentName);
            saveAddPackage(agentNames, channel, channelAddPackage, price, request);
        }
        return JsonData.buildSuccess();
    }

    private void saveAddPackage(List<String> agentNames, Channel channel, ChannelAddPackage channelAddPackage, double price, HttpServletRequest request) {
        AgentAddPackage agentAddPackage = null;
        Agent agent = null;
        for (String agentName : agentNames) {
            agent = agentService.findByName(agentName);
            if (null == agent)
                continue;
            agentAddPackage = new AgentAddPackage();
            BeanUtils.copyProperties(channelAddPackage, agentAddPackage);
            agentAddPackage.setAgentName(agentName);
            agentAddPackage.setPackagePrice(price);
            agentAddPackage.setRebateMode(agent.getRebateMode());
            agentAddPackage.setTotalStatus(agentAddPackageService.AgentTotalStatus(channel.getStatus(), agent.getStatus(), channelAddPackage.getStatus()));
            agentAddPackageService.addAgentPackage(agentAddPackage);
            showLog(request, "添加了代理商加油包", agentAddPackage.toString());
            logUtil.addSystemUserLog(request, "添加了代理商加油包", "增加");
        }


    }

    @GetMapping("/api/v1/agentAddPackage_totalStatus")
    public JsonData reloadTotalStatus() {
        agentAddPackageService.updateAllTotalStatus();
        return JsonData.buildSuccess();
    }


    @GetMapping("/api/v1/agentAddPackage_list")
    public PageInfo<AgentAddPackage> findAgentTariffList(int page, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode) {
        return agentAddPackageService.findAgentTariffList(page, 20, operator, channelName, showName, agentName, packageId, status, feeMode);
    }

}

