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

import com.weida.pojo.AgentTariff;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/21
 * Time:13:46
 */
public interface AgentTariffDao {

    /**
     * 添加代理商资费计划
     * @param agentTariff 代理商资费计划pojo类
     */
    @InsertProvider(type = ProviderSql.class, method = "addAgentTariff")
    void addAgentTariff(AgentTariff agentTariff);

    /**
     * 通过条件查询代理商资费计划
     * @param operator 运营商
     * @param channelName 通道名称
     * @param showName 展示名称
     * @param agentName 代理名称
     * @param packageId 套餐编码
     * @param status 状态
     * @param feeMode 计费模式
     * @param superpositionMode 叠加模式
     * @return 代理资费计划集合
     */
    @SelectProvider(type = ProviderSql.class, method = "findAgentTariff")
    List<AgentTariff> findAgentTariff(Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode, Integer superpositionMode);

    /**
     * 根据id 更新价格
     * @param id id
     * @param price 价格
     */
    @Update("update iot_agent_tariff set tariffPrice = #{price} where id = #{id}")
    void updatePrice(Integer id, Double price);

    /**
     * 根据id 删除资费计划
     * @param id id
     */
    @Delete("delete from iot_agent_tariff where id = #{id}")
    void deleteById(Integer id);

    /**
     * 获取所有的代理商资费计划
     * @return 资费计划集合
     */
    @Select("select * from iot_agent_tariff")
    List<AgentTariff> findAllAgentTariff();

    /**
     * 更新总的状态
     * @param totalStatus 总状态
     */
    @Update("update iot_agent_tariff set totalStatus = #{totalStatus} where id = #{id}")
    void updateTotalStatus(int totalStatus, int id);
//
//    @UpdateProvider(type = ProviderSql.class, method = "updateAgentTariff")
//    void updateAgentTariff(AgentTariff agentTariff);


}
