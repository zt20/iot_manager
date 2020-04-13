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
import com.weida.pojo.AgentAddPackage;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/25
 * Time:10:44
 */
public interface AgentAddPackageService {


    int TOTAL_STATUS_DEFAULT = 0; //默认总状态 供应商和代理,套餐资费计划状态未知
    int TOTAL_STATUS_ALL_OK = 1; //所有状态都准备ok
    int TOTAL_STATUS_CHANNEL_NO = 2;// 供应商状态未就绪
    int TOTAL_STATUS_AGENT_NO = 3; //代理商状态未就绪
    int TOTAL_STATUS_CHANNEL_TARIFF_NO = 4;//资费未就绪

    /**
     * 添加代理商加油包
     * @param agentAddPackage 代理商加油包pojo类
     */
    void addAgentPackage(AgentAddPackage agentAddPackage);

    /**
     * 通过id 来删除代理商加油包配置
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * 更新价格
     * @param id
     * @param price
     */
    void updatePrice(Integer id, Double price);

    /**
     * 获取代理商资费计划的总状态
     * @param channelStatus 通道状态
     * @param agentStatus 代理商状态
     * @param channelAddPackageStatus 通道资费计划状态
     * @return 总的状态
     */
    int AgentTotalStatus(int channelStatus, int agentStatus, int channelAddPackageStatus);

    /**
     * 更新所有代理商加油包的总状态
     */
    void updateAllTotalStatus();

    /**
     * 获取所有的代理商加油包
     * @return 所有加油包
     */
    List<AgentAddPackage> findAllAddPackage();

    /**
     * 更新代理商加油包的总状态
     * @param totalStatus 总状态
     * @param id id
     */
    void updateTotalStatus(int totalStatus, Integer id);

    /**
     * 根据条件来查找代理商加油包 并且分页
     * @param page 页码
     * @param pageSize 每页数
     * @param operator 运营商
     * @param channelName 通道名称
     * @param showName 展示名称
     * @param agentName 代理商名称
     * @param packageId 套餐编码
     * @param status 状态
     * @param feeMode 计费模式
     * @return
     */
    PageInfo<AgentAddPackage> findAgentTariffList(int page, int pageSize, Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode);
}
