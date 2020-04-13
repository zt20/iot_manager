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
package com.weida.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.dao.ChannelDao;
import com.weida.pojo.Channel;
import com.weida.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/14
 * Time:11:38
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDao channelDao;

    @Override
    public void addChannel(Channel channel) {
        channelDao.addChannel(channel);
    }

    @Override
    public void updateChannel(Channel channel) {
        channelDao.updateChanel(channel);
    }

    @Override
    public Channel findById(Integer id) {
        return channelDao.findById(id);
    }

    @Override
    public boolean findByChannelName(String channelName) {
        return channelDao.findByChannelName(channelName) != null;
    }

    @Override
    public PageInfo<Channel> findChannelList(int pageNum, int pageSize,String channelName, Integer status, Integer operator) {
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<Channel> channels = channelDao.findChannelList(channelName, status, operator);
        PageInfo<Channel> page = new PageInfo<>(channels);
        return page;
    }

    @Override
    public void updateChannelStatus(int id, int action) {
        channelDao.updateChannelStatus(id, action);
    }

    @Override
    public List<String> findAllChannel() {
        return channelDao.findAllChannel();
    }

    @Override
    public Channel getByChannelName(String channelName) {
        return channelDao.findByChannelName(channelName);
    }
}
