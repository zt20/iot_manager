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
import com.weida.pojo.ChannelTariff;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/18
 * Time:14:11
 */
public interface ChannelTariffService {

    /**
     * 添加 通道资费计划
     *
     * @param channelTariff 通道资费pojo类
     */
    void addChannelTariff(ChannelTariff channelTariff);

    /**
     * 更新通道资费计划
     *
     * @param channelTariff 通道资费pojo类
     */
    void updateChannelTariff(ChannelTariff channelTariff);

    /**
     * @param page         页数
     * @param pageSize     一页多少
     * @param channelName  通道名称
     * @param packageId    套餐ID
     * @param showName     展示名称
     * @param originalName 原始名称
     * @param operator     运营商
     * @return 返回分页集合
     */
    PageInfo<ChannelTariff> channelTariffList(int page, int pageSize, String channelName, String packageId, String showName, String originalName, Integer operator);

    /**
     * 通过ID获取资费计划
     *
     * @param id id
     * @return 返回资费计划
     */
    ChannelTariff findById(Integer id);

    /**
     * 判断是否存在这个套餐编码
     * @param channelTariff 通道资费计划pojo类
     * @return 是否存在
     */
    boolean isExistPackageId(ChannelTariff channelTariff);

    /**
     * 通过通道名称获取所有的资费计划的套餐包
     * @param channelName 通道名称
     * @return 资费计划套餐包集合
     */
    List<String> findByChannelName(String channelName);

    /**
     * 判断该通道是否有该套餐编码
     * @param channelName 通风名称
     * @param packageId 套餐编码
     * @return true 存在， false 不存在
     */
    boolean channelExistPackageId(String channelName, String packageId);


    /**
     * 通过packageId获取通道资费计划
     * @param packageId 套餐编码
     * @return 通道资费计划
     */
    ChannelTariff findByPackageId(String packageId);
}
