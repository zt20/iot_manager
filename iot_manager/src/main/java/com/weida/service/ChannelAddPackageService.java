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
import com.weida.pojo.ChannelAddPackage;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/20
 * Time:11:06
 */
public interface ChannelAddPackageService {

    /**
     * 添加通道加油包
     * @param channelAddPackage 通道加油包pojo类
     */
   void addChannelAddPackage(ChannelAddPackage channelAddPackage);

    /**
     * 判断通道加油包编码是否存在
     * @param channelAddPackage 通道加油包pojo类
     * @return 如果存在返回true 否之false
     */
   boolean isExistPackageId(ChannelAddPackage channelAddPackage);

    /**
     * 更新通道加油包
     * @param channelAddPackage 通道加油包pojo类
     */
    void updateChannelAddPackage(ChannelAddPackage channelAddPackage);

    /**
     * 通过id查找通道加油包
     * @param id 主键id
     * @return 加油包类
     */
    ChannelAddPackage findById(Integer id);

    /**
     * 通过条件来查找通道加油包
     * @param page 页数
     * @param pageSize 每页多少条数据
     * @param channelName 通道名称
     * @param packageId 加油包编码
     * @param showName 展示名称
     * @param originalName 原始名称
     * @param operator 运营商
     * @param status 状态
     * @return 返回通道加油包集合
     */
    PageInfo<ChannelAddPackage> findAddPackageList(int page, int pageSize, String channelName, String packageId, String showName, String originalName, Integer operator, Integer status);

    /**
     * 通过packageId 来查找通道加油包
     * @param packageId 套餐编码
     * @return 通道加油包
     */
    ChannelAddPackage findByPackageId(String packageId);

    /**
     * 通过通道名称获取通道加油包
     * @param channelName 通道名称
     * @return 通道加油包
     */
    List<String> findByChannelName(String channelName);
}
