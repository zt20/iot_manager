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

import com.weida.pojo.ChannelTariff;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/18
 * Time:10:54
 */
public interface ChannelTariffDao {

    /**
     * 添加通道资费计划
     * @param channelTariff 通道资费pojo类
     */
    @InsertProvider(type = ProviderSql.class, method = "addChannelTariff")
    void addChannelTariff(ChannelTariff channelTariff);

    /**
     * 更新通道资费计划
     * @param channelTariff 通道资费pojo类
     */
    @UpdateProvider(type = ProviderSql.class, method = "updateChannelTariff")
    void updateChannelTariff(ChannelTariff channelTariff);

    /**
     * 根据传进来的参数 来查找具体资费集合
     * @param channelName 通道名称
     * @param packageId 套餐编码
     * @param showName 展示名称
     * @param originalName 原始名称
     * @param operator 运营商
     * @return 返回资费集合
     */
    @SelectProvider(type =  ProviderSql.class, method = "findChannelTariff")
    List<ChannelTariff> findChannelTariff(String channelName, String packageId, String showName, String originalName, Integer operator);

    /**
     * 通过id返回具体的资费计划
     * @param id id
     * @return 资费计划
     */
    @Select("select * from iot_channel_tariff where id = #{id}")
    ChannelTariff findById(Integer id);

    /**
     * 通过套餐编码查找通道资费计划
     * @param packageId 套餐编码
     * @return  通道计划
     */
    @Select("select * from iot_channel_tariff where packageId = #{packageId}")
    ChannelTariff findByPackageId(String packageId);

    /**
     * 通过通道名称获取套餐包集合
     * @param channelName 通道名称
     * @return 套餐包集合
     */
    @Select("select packageId from iot_channel_tariff where channelName = #{channelName}")
    List<String> findByChannelName(String channelName);

    /**
     * 通过通道名称和套餐编码来查找资费计划
     * @param channelName 通道名称
     * @param packageId 套餐编码
     * @return 通道资费计划
     */
    @Select("select * from iot_channel_tariff where channelName = #{channelName} and packageId = #{packageId}")
    ChannelTariff channelExistPackageId(String channelName, String packageId);
}
