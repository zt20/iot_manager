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
import com.weida.dao.AgentDao;
import com.weida.pojo.Agent;
import com.weida.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/8
 * Time:10:03
 */
@Service
@Transactional
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentDao agentDao;

    /**
     * 增加代理商
     *
     * @param agent
     */
    @Override
    public void addAgents(Agent agent) {
        agentDao.addAgents(agent);
    }

    /**
     * 更新代理商信息
     *
     * @param agent
     */
    @Override
    public void updateAgent(Agent agent) {
        agentDao.updateAgent(agent);
    }

    /**
     * 根据id 删除代理商
     *
     * @param id
     */
    @Override
    public void deleteAgent(int id) {
        agentDao.deleteAgent(id);
    }

    /**
     * 返回代理商列表
     *
     * @param id
     * @param agentAccount
     * @param name
     * @param status
     * @return
     */
    @Override
    public PageInfo<Agent> selectAgent(int pageNum, int pageSize, int id, String agentAccount, String name, int status) {
        PageHelper.startPage(pageNum, pageSize, "addTime desc");
        List<Agent> agents = agentDao.selectAgent(id, agentAccount, name, status);
        PageInfo<Agent> page = new PageInfo<>(agents);
        return page;
    }

    /**
     * 判断该代理商是否存在
     *
     * @param agentAccount
     * @return
     */
    @Override
    public boolean findByAgentAccount(String agentAccount) {
        return agentDao.findByAgentAccount(agentAccount) != null;
    }

    /**
     * 根据id 查找代理商
     *
     * @param id
     * @return
     */
    @Override
    public Agent findByAgentId(int id) {
        return agentDao.findByAgentId(id);
    }

    /**
     * 批量更新代理商的状态
     *
     * @param action
     * @param ids
     */
    @Override
    public void updateAgentStatus(int action, ArrayList<Integer> ids) {
        for (Integer id : ids) {
            this.updateAgentStatus(action, id);
        }
    }

    /**
     * 单次更新代理商状态
     *
     * @param action
     * @param id
     */
    @Override
    public void updateAgentStatus(int action, Integer id) {
        agentDao.updateAgentStatus(action, id);
    }


    @Override
    public List<String> findAllAgentName() {
       return  agentDao.findAllAgentName();
    }

    @Override
    public Agent findByName(String agentName) {
        return agentDao.findByName(agentName);
    }

    @Override
    public boolean addAgentMoney(String account, double toDouble) {
        try {
            agentDao.addAgentMoney(account, toDouble);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
