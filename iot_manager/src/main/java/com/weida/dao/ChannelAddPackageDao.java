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

import com.weida.pojo.ChannelAddPackage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/20
 * Time:10:44
 */
public interface ChannelAddPackageDao {

    /**
     * 添加通道加油包
     * @param channelAddPackage 加油包pojo类
     */
    @SelectProvider(type = ProviderSql.class, method = "addChannelAddPackage")
    void addChannelAddPackage(ChannelAddPackage channelAddPackage);

    /**
     * 更新通道加油包
     * @param channelAddPackage 加油包pojo类
     */
    @UpdateProvider(type = ProviderSql.class, method = "updateChannelAddPackage")
    void updateChannelPackage(ChannelAddPackage channelAddPackage);

    /**
     * 根据id获取通道加油包
     * @param id id
     * @return 返回通道加油包
     */
    @Select("select * from iot_channel_addPackage where id = #{id}")
    ChannelAddPackage findById(Integer id);

    /**
     * 根据packageId获取通道加油包
     * @param packageId 加油包编码
     * @return 返回通道加油包
     */
    @Select("select * from iot_channel_addPackage where packageId = #{packageId}")
    ChannelAddPackage findByPackageId(String packageId);


    /**
     * 根据条件查找
     * @param channelName 所属通道的名称
     * @param packageId 套餐包编码
     * @param showName 展示名称
     * @param originalName 原始名称
     * @param operator 运营商
     * @param status 状态
     * @return 通道加油包集合
     */
    @SelectProvider(type = ProviderSql.class, method = "findChannelAddPackage")
    List<ChannelAddPackage> findChannelAddPackage(String channelName, String packageId, String showName, String originalName, Integer operator, Integer status);

    /**
     * 根据通道名称获取通道加油包
     * @param channelName 通道名称
     * @return 通道加油包
     */
    @Select("select packageId from iot_channel_addPackage where channelName = #{channelName}")
    List<String> findByChannelName(String channelName);
}
