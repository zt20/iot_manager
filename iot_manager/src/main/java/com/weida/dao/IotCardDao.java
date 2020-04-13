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

import com.weida.pojo.IotCard;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/26
 * Time:10:42
 */
public interface IotCardDao {

    /**
     * 添加物联网卡
     * @param iotCard 物联网卡pojo类
     */
    @InsertProvider(type = ProviderSql.class ,method = "addIotCard")
    void addIotCard(IotCard iotCard);

    /**
     * 删除物理网卡 根据卡号
     * @param iotCard 卡号
     */
    @Delete("delete form iot_card where iotCard = #{iotCard}")
    void deleteByIotCard(String iotCard);

    /**
     * 根据卡号来查找物联网卡
     * @param iotCard 卡号
     * @return 物料卡信息
     */
    @Select("select * from iot_card where iotCard = #{iotCard}")
    IotCard findByIotCard(String iotCard);

    /**
     * 根据条件查询
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
    @SelectProvider(type = ProviderSql.class, method = "findByChooseList")
    List<IotCard> findByChooseList(String startCardId, String endCardId, String cardId, Integer operator, Integer status, String channelName, String agentName, Integer trafficPool, Integer networkStatus);

    /**
     * 更新使用量
     * @param cardId 卡号
     * @param total 总量
     * @param use 使用量
     */
    @Update("update iot_card set totalSize = #{total    }, useSize = #{use} where cardId = #{cardId}")
    void updateTrafficUse(String cardId, String total, String use);

    /**
     * 更新卡号的代理商
     * @param id id
     * @param agentAccount 代理商
     */
    @Update("update iot_card set agentName = #{agentAccount} where id = #{id}")
    void updateAgent(int id, String agentAccount);

    /**
     * 根据id更新卡号的状态
     * @param id id
     * @param status 状态
     */
    @Update("update iot_card set status = #{status} where id = #{id}")
    void updateStatus(int id, Integer status);

    /**
     * 根据id更新网络状态
     * @param id id
     * @param networkStatus 网络状态
     */
    @Update("update iot_card set networkStatus = #{networkStatus} where id = #{id}")
    void updateNetwork(int id, Integer networkStatus);

    /**
     * 根据id更新卡类型
     * @param id id
     * @param cardType 卡类型
     */
    @Update("update iot_card set cardType = #{cardType} where id = #{id}")
    void updateCardType(int id, Integer cardType);

    /**
     * 根据id 更新是否是流量池
     * @param id id
     * @param trafficPool 流量池
     */
    @Update("update iot_card set trafficPool = #{trafficPool} where id = #{id}")
    void updateTrafficPool(int id, Integer trafficPool);
}
