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

import com.weida.pojo.Channel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/14
 * Time:10:44
 */
public interface ChannelDao {

    @InsertProvider(type = ProviderSql.class, method = "addChannelSql")
    void addChannel(Channel channel);

    @UpdateProvider(type = ProviderSql.class, method = "updateChannel")
    void updateChanel(Channel channel);

    @Select("select * from iot_channel where id = #{id}")
    Channel findById(Integer id);

    @Select("select * from iot_channel where channelName = #{channelName}")
    Channel findByChannelName(String channelName);

    @SelectProvider(type = ProviderSql.class, method = "findChannelList")
    List<Channel> findChannelList(String channelName, Integer status, Integer operator);

    @Update("update iot_channel set status = #{status} where id = #{id}")
    void updateChannelStatus(int id, int status);

    @Select("select channelName from iot_channel where 1 = 1")
    List<String> findAllChannel();

}
