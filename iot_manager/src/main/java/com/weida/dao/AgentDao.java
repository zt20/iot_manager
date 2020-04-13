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
package com.weida.dao;

import com.weida.pojo.Agent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/7
 * Time:15:59
 * 代理商dao层
 */

public interface AgentDao {

    @InsertProvider(type = ProviderSql.class, method = "addAgentSql")
    void addAgents(Agent agent);

    @UpdateProvider(type = ProviderSql.class, method = "updateAgentSql")
    void updateAgent(Agent agent);

    @Delete("delete from iot_agents where id = #{id}")
    void deleteAgent(int id);

    @SelectProvider(type = ProviderSql.class, method = "selectAgentSql")
    List<Agent> selectAgent(int id, String agentAccount, String name, int status);

    @Select("select * from iot_agents where agentAccount = #{agentAccount}")
    Agent findByAgentAccount(String agentAccount);

    @Select("select * from iot_agents where id = #{id}")
    Agent findByAgentId(int id);

    @Update("update iot_agents set status = #{status} where id = #{id}")
    void updateAgentStatus(int status, int id);

    @Select("select name from iot_agents where 1 = 1")
    List<String> findAllAgentName();

    /**
     * 通过名称获取代理商
     * @param name 代理商名称
     * @return 代理商
     *
     * */
    @Select("select * from iot_agents where 1 = 1 and name = #{name} ")
    Agent findByName(String name);

    /**
     * 添加
     * @param account
     * @param addMoney
     */
    @Update("update iot_agents set balance = balance + #{addMoney} where agentAccount = #{account}")
    void addAgentMoney(String account, double addMoney);
}
