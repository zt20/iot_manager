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
import com.weida.pojo.IotCard;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/26
 * Time:10:58
 */
public interface IotCardService {

    /**
     * 添加物联网卡
     *
     * @param iotCard 物联卡pojo类
     */
    void addIotCard(IotCard iotCard);

    /**
     * 批量添加物联网卡号
     *
     * @param iotCards 网卡集合
     */
    void addIotCards(List<IotCard> iotCards);

    /**
     * 批量删除物联卡
     *
     * @param iotCards 网卡集合
     */
    void deleteByIotCards(List<String> iotCards);

    /**
     * 根据卡号来删除物联网卡(会判断这个卡号是否存在)
     *
     * @param iotCard 物料网卡卡号
     */
    void deleteByIotCard(String iotCard);

    /**
     * 通过卡号来查找物联网卡
     *
     * @param iotCard 卡号
     * @return 物理网卡信息
     */
    IotCard findByIotCard(String iotCard);

    /**
     * 根据条件筛选物联网卡
     *
     * @param startCardId   开始卡号
     * @param endCardId     结束卡号
     * @param cardId        卡号
     * @param operator      运营商
     * @param status        状态
     * @param channelName   通道名称
     * @param agentName     代理商名称
     * @param trafficPool   是否是流量池
     * @param networkStatus 网络状态
     * @return 物联网卡集合
     */
    PageInfo<IotCard> findByChooseList(int page, int pageSize, String startCardId, String endCardId, String cardId, Integer operator, Integer status, String channelName, String agentName, Integer trafficPool, Integer networkStatus);

    /**
     * 更新使用量
     * @param useTraffic map集合
     */
    void updateTrafficUse(Map<String, List<String>> useTraffic);

    /**
     * 根据条件筛选复核条件的物联网卡
     * @param smsisdn 开始卡号
     * @param emsisdn 结束卡号
     * @param msisdn 卡号
     * @param operatorType 运营商
     * @param status 状态
     * @param agentAccount 代理商
     * @param interfaceID 供应商
     * @return 物联网卡集合
     */
    List<IotCard> findIotCards(String smsisdn, String emsisdn, String msisdn, Integer operatorType, Integer status, String agentAccount, String interfaceID);

    /**
     * 更新该卡号的代理商
     * @param id id
     * @param agentAccount 代理商
     */
    void updateAgent(int id, String agentAccount);

    /**
     * 根据id更新卡号的状态
     * @param id id
     * @param status 状态
     */
    void updateStatus(int id, Integer status);

    /**
     * 根据id更新网络状态
     * @param id id
     * @param networkStatus 网络状态
     */
    void updateNetwork(int id, Integer networkStatus);

    /**
     * 根据id 更新card类型
     * @param id id
     * @param cardType 类型
     */
    void updateCardType(int id, Integer cardType);

    /**
     * 根据id更新是否流量池
     * @param id id
     * @param trafficPool 流量池
     */
    void updateTrafficPool(int id, Integer trafficPool);
}
