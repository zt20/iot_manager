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
import com.weida.pojo.Channel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/14
 * Time:11:36
 */
public interface ChannelService {

    int CHANNEL_OK = 0;
    int CHANNEL_STOP = 1;

    /**
     * 添加通道
     *
     * @param channel 通道pojo类
     */
    void addChannel(Channel channel);

    /**
     * 更新通道
     *
     * @param channel 通道pojo类
     */
    void updateChannel(Channel channel);

    /**
     * 更加id 来查询通道
     *
     * @param id id
     * @return 返回通道类
     */
    Channel findById(Integer id);


    /**
     * 判断是否有这个通道的存在 根据名称来查询
     *
     * @param channelName 通道名称
     * @return true是有 false是没有
     */
    boolean findByChannelName(String channelName);

    /**
     * 分页和条件查找通道
     *
     * @param page        页码数
     * @param channelName 通道名称
     * @param status      状态
     * @param operator    运营商
     * @return 分页类 用于controller 调用
     */
    PageInfo<Channel> findChannelList(int page, int pageSize, String channelName, Integer status, Integer operator);


    /**
     * 更新通道状态
     *
     * @param id     id
     * @param action 动作是停止还是启动
     */
    void updateChannelStatus(int id, int action);


    /**
     * 获取所有的通道名称 供前端使用
     *
     * @return 所有的通道名称
     */
    List<String> findAllChannel();

    /**
     * 通过通道名称获取通道的实例
     * @param channelName 通道名称
     * @return 通道实例
     */
    Channel getByChannelName(String channelName);
}
