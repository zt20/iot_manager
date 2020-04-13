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
import com.weida.dao.AgentAddPackageDao;
import com.weida.pojo.Agent;
import com.weida.pojo.AgentAddPackage;
import com.weida.pojo.Channel;
import com.weida.pojo.ChannelAddPackage;
import com.weida.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/25
 * Time:10:50
 */
@Service
@Transactional
public class AgentAddPackageServiceImpl implements AgentAddPackageService {

    @Autowired
    AgentAddPackageDao agentAddPackageDao;

    @Autowired
    ChannelService channelService;

    @Autowired
    AgentService agentService;

    @Autowired
    ChannelAddPackageService channelAddPackageService;

    @Override
    public void addAgentPackage(AgentAddPackage agentAddPackage) {
        agentAddPackageDao.addAgentAddPackage(agentAddPackage);
    }

    @Override
    public void deleteById(Integer id) {
        agentAddPackageDao.deleteById(id);
    }

    @Override
    public void updatePrice(Integer id, Double price) {
        agentAddPackageDao.updatePrice(id, price);
    }

    @Override
    public int AgentTotalStatus(int channelStatus, int agentStatus, int channelAddPackageStatus) {
        if (channelStatus == 0 && agentStatus == 0 && channelAddPackageStatus == 0)
            return AgentTariffService.TOTAL_STATUS_ALL_OK;
        if (channelStatus != 0)
            return AgentTariffService.TOTAL_STATUS_CHANNEL_NO;
        if (agentStatus != 0)
            return AgentTariffService.TOTAL_STATUS_AGENT_NO;
        if (channelAddPackageStatus != 0)
            return AgentTariffService.TOTAL_STATUS_CHANNEL_TARIFF_NO;
        return AgentTariffService.TOTAL_STATUS_DEFAULT;
    }


    @Override
    public void updateAllTotalStatus() {
        List<AgentAddPackage> agentAddPackages = this.findAllAddPackage();
        Agent agent = null;
        Channel channel = null;
        ChannelAddPackage channelAddPackage = null;
        int totalStatus;
        for (AgentAddPackage addPackage : agentAddPackages) {
            if (StringUtils.isBlank(addPackage.getAgentName()) || StringUtils.isBlank(addPackage.getChannelName()) || StringUtils.isBlank(addPackage.getPackageId()))
                continue;
            agent = agentService.findByName(addPackage.getAgentName());
            channel = channelService.getByChannelName(addPackage.getChannelName());
            channelAddPackage = channelAddPackageService.findByPackageId(addPackage.getPackageId());
            totalStatus = this.AgentTotalStatus(channel.getStatus(), agent.getStatus(), channelAddPackage.getStatus());
            if (totalStatus != addPackage.getTotalStatus()) {
                this.updateTotalStatus(totalStatus, addPackage.getId());
            }
        }
    }

    @Override
    public List<AgentAddPackage> findAllAddPackage() {
        return agentAddPackageDao.findAllAgentPackage();
    }

    @Override
    public void updateTotalStatus(int totalStatus, Integer id) {
        agentAddPackageDao.updateAgentAddPackageTotal(id, totalStatus);
    }

    @Override
    public PageInfo<AgentAddPackage> findAgentTariffList(int page, int pageSize, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode) {
        PageHelper.startPage(page, pageSize, "id desc");
        List<AgentAddPackage> agentAddPackages = agentAddPackageDao.findAgentAddPackage(operator, channelName, showName, agentName, packageId, status, feeMode);
        PageInfo<AgentAddPackage> pageInfo = new PageInfo<>(agentAddPackages);
        return pageInfo;
    }
}
