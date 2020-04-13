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
package com.weida.service;


import com.github.pagehelper.PageInfo;
import com.weida.pojo.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/8
 * Time:9:55
 */
public interface AgentService {

    int AGENT_OK = 0;

    int AGENT_STOP = 1;

    void addAgents(Agent agent);

    void updateAgent(Agent agent);

    void deleteAgent(int id);

    PageInfo<Agent> selectAgent(int pageNum, int pageSize, int id, String agentAccount, String name, int status);

    boolean findByAgentAccount(String agentAccount);

    Agent findByAgentId(int id);

    void updateAgentStatus(int action, ArrayList<Integer> ids);

    void updateAgentStatus(int action, Integer id);

    /**
     *查找所有的代理商的名称
     * @return 代理商名称集合
     */
    List<String> findAllAgentName();

    /**
     * 通过代理商名称获取代理商
     * @param agentName 代理商名称
     */
    Agent findByName(String agentName);

    /**
     * 给代理商加钱
     * @param account
     * @param toDouble
     * @return
     */
    boolean addAgentMoney(String account, double toDouble);
}
