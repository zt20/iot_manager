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
import com.weida.pojo.AgentTariff;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/21
 * Time:14:03
 */
public interface AgentTariffService {

    int TOTAL_STATUS_DEFAULT = 0; //默认总状态 供应商和代理,套餐资费计划状态未知
    int TOTAL_STATUS_ALL_OK = 1; //所有状态都准备ok
    int TOTAL_STATUS_CHANNEL_NO = 2;// 供应商状态未就绪
    int TOTAL_STATUS_AGENT_NO = 3; //代理商状态未就绪
    int TOTAL_STATUS_CHANNEL_TARIFF_NO = 4;//资费未就绪

    /**
     * 添加代理商资费计划
     * @param agentTariff 代理商资费计划pojo
     */
    void addAgentTariff(AgentTariff agentTariff);


    /**
     * 获取代理商资费计划的总状态
     * @param channelStatus 通道状态
     * @param agentStatus 代理商状态
     * @param channelTariffStatus 通道资费计划状态
     * @return 总的状态
     */
    int AgentTotalStatus(int channelStatus, int agentStatus, int channelTariffStatus);

    /**
     * 代理商资费计划分页
     * @param page 页码数
     * @param pageSize 每页多少
     * @param operator 运营商
     * @param channelName 供应商
     * @param showName 展示名称
     * @param agentName 代理商名称
     * @param packageId 套餐包编码
     * @param status 状态
     * @param feeMode 计费模式
     * @param superpositionMode 叠加模式
     * @return 分页类
     */
    PageInfo<AgentTariff> findAgentTariffList(int page, int pageSize, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode, Integer superpositionMode);

    /**
     * 根据id来更新价格
     * @param id id
     * @param price 价格
     */
    void updatePrice(Integer id, Double price);

    /**
     * 根据id 删除数据
     * @param id  id
     */
    void deleteById(Integer id);

    /**
     * 获取所有的代理商资费计划
     * @return 所有的代理商资费计划
     */
    List<AgentTariff> findAllAgentTariff();

    /**
     * 更新总状态
     * @param totalStatus 总状态
     */
    void updateTotalStatus(int totalStatus, int id);

    /**
     * 更新所有的总状态
     */
    void updateAllTotalStatus();
}
