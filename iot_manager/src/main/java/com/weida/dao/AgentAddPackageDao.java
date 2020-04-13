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

import com.weida.pojo.AgentAddPackage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/25
 * Time:9:58
 */
public interface AgentAddPackageDao {

    /**
     * 添加代理商加油包
     * @param agentAddPackage 代理商加油包pojo类
     */
    @InsertProvider(type = ProviderSql.class, method = "addAgentAddPackage")
    void addAgentAddPackage(AgentAddPackage agentAddPackage);

    /**
     * 根据id 删除数据
     * @param id id
     */
    @Delete("delete from iot_agent_addPackage where id = #{id}")
    void deleteById(Integer id);

    /**
     * 通过条件查询代理商加油包计划
     * @param operator 运营商
     * @param channelName 通道名称
     * @param showName 展示名称
     * @param agentName 代理名称
     * @param packageId 套餐编码
     * @param status 状态
     * @param feeMode 计费模式
     * @return 代理加油包集合
     */
    @SelectProvider(type = ProviderSql.class, method = "findAgentAddPackage")
    List<AgentAddPackage> findAgentAddPackage(Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode);

    /**
     * 更新价格
     * @param id id
     * @param price 价格
     */
    @Update("update iot_agent_addPackage set packagePrice = #{price} where id = #{id}")
    void updatePrice(Integer id, Double price);

    /**
     * 获取所有的代理商
     * @return 所有代理商
     */
    @Select("select * from iot_agent_addPackage")
    List<AgentAddPackage> findAllAgentPackage();

    /**
     * 更新代理商加油包的总状态
     * @param id id
     * @param totalStatus 代理加油包的总状态
     */
    @Update("update iot_agent_addPackage set totalStatus = #{totalStatus} where id = #{id}")
    void updateAgentAddPackageTotal(int id, int totalStatus);

}
