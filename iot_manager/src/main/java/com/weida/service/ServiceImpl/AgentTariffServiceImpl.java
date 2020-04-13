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
package com.weida.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.dao.AgentTariffDao;
import com.weida.pojo.Agent;
import com.weida.pojo.AgentTariff;
import com.weida.pojo.Channel;
import com.weida.pojo.ChannelTariff;
import com.weida.service.AgentService;
import com.weida.service.AgentTariffService;
import com.weida.service.ChannelService;
import com.weida.service.ChannelTariffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/21
 * Time:14:05
 */
@Service
@Transactional
public class AgentTariffServiceImpl implements AgentTariffService {

    @Autowired
    AgentTariffDao agentTariffDao;

    @Autowired
    ChannelService channelService;

    @Autowired
    AgentService agentService;

    @Autowired
    ChannelTariffService channelTariffService;

    @Override
    public void addAgentTariff(AgentTariff agentTariff) {
        agentTariffDao.addAgentTariff(agentTariff);
    }

    @Override
    public int AgentTotalStatus(int channelStatus, int agentStatus, int channelTariffStatus) {
        if (channelStatus == 0 && agentStatus == 0 && channelTariffStatus == 0)
            return AgentTariffService.TOTAL_STATUS_ALL_OK;
        if (channelStatus != 0)
            return AgentTariffService.TOTAL_STATUS_CHANNEL_NO;
        if (agentStatus != 0)
            return AgentTariffService.TOTAL_STATUS_AGENT_NO;
        if (channelTariffStatus != 0)
            return AgentTariffService.TOTAL_STATUS_CHANNEL_TARIFF_NO;
        return AgentTariffService.TOTAL_STATUS_DEFAULT;
    }

    @Override
    public PageInfo<AgentTariff> findAgentTariffList(int page, int pageSize, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode, Integer superpositionMode) {
        PageHelper.startPage(page, pageSize, "id desc");
        List<AgentTariff> agentTariffs = agentTariffDao.findAgentTariff(operator, channelName, showName, agentName, packageId, status, feeMode, superpositionMode);
        PageInfo<AgentTariff> pageInfo = new PageInfo<>(agentTariffs);
        return pageInfo;
    }

    @Override
    public void updatePrice(Integer id, Double price) {
        agentTariffDao.updatePrice(id, price);
    }

    @Override
    public void deleteById(Integer id) {
        agentTariffDao.deleteById(id);
    }

    @Override
    public List<AgentTariff> findAllAgentTariff() {
        return agentTariffDao.findAllAgentTariff();
    }

    @Override
    public void updateTotalStatus(int totalStatus, int id) {
        agentTariffDao.updateTotalStatus(totalStatus, id);
    }

    @Override
    public void updateAllTotalStatus() {
        List<AgentTariff> agentTariffs = this.findAllAgentTariff();
        Agent agent = null;
        Channel channel = null;
        ChannelTariff channelTariff = null;
        int totalStatus;
        for (AgentTariff agentTariff : agentTariffs) {
            if (StringUtils.isBlank(agentTariff.getAgentName()) || StringUtils.isBlank(agentTariff.getChannelName()) || StringUtils.isBlank(agentTariff.getPackageId()))
                continue;
            agent = agentService.findByName(agentTariff.getAgentName());
            channel = channelService.getByChannelName(agentTariff.getChannelName());
            channelTariff = channelTariffService.findByPackageId(agentTariff.getPackageId());
            totalStatus = this.AgentTotalStatus(channel.getStatus(), agent.getStatus(),channelTariff.getStatus());
            if (totalStatus != agentTariff.getTotalStatus()){
                this.updateTotalStatus(totalStatus, agentTariff.getId());
            }
        }
    }
}
