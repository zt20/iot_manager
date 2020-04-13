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
package com.weida.controller;

import com.github.pagehelper.PageInfo;
import com.weida.domain.JsonData;
import com.weida.pojo.Channel;
import com.weida.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/14
 * Time:12:13
 */
@RestController
public class ChannelController extends BaseController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/api/v1/channel_add")
    public JsonData addChannel(Channel channel, HttpServletRequest request) {
        if (channelService.findByChannelName(channel.getChannelName())) {
            return JsonData.buildFail("该通道已经存在", null);
        }
        showLog(request, "添加了通道", channel);
        logUtil.addSystemUserLog(request, "添加了通道", "增加");
        channelService.addChannel(channel);
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/channel_update")
    public JsonData updateChannel(Channel channel, HttpServletRequest request) {

        if (channelService.findById(channel.getId()) == null) {
            return JsonData.buildFail("该通道已经不存在", null);
        }
        showLog(request, "修改了通道", channel);
        logUtil.addSystemUserLog(request, "修改了通道", "修改");
        channelService.updateChannel(channel);
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/channel_list")
    public PageInfo findChannels(int page, String channelName, Integer status, Integer operator) {
        return channelService.findChannelList(page, 15, channelName, status, operator);
    }

    @PostMapping("/api/v1/channel_updateStatus")
    public JsonData updateChannelStatus(Integer id, String action, HttpServletRequest request) {
        if (channelService.findById(id) == null) {
            return JsonData.buildFail("该通道已经不存在", null);
        }
        if ("ok".equals(action)) {
            channelService.updateChannelStatus(id, ChannelService.CHANNEL_OK);
            showLog(request, "修改了通道状态", id);
            logUtil.addSystemUserLog(request, "通道状态", "修改");
            return JsonData.buildSuccess();
        }
        if ("stop".equals(action)) {
            channelService.updateChannelStatus(id, ChannelService.CHANNEL_STOP);
            showLog(request, "修改了通道状态", id);
            logUtil.addSystemUserLog(request, "通道状态", "修改");
            return JsonData.buildSuccess();
        }
        return JsonData.buildFail("操作有误", null);
    }

    @GetMapping("/api/v1/channel_id")
    public JsonData findById(Integer id) {
        Channel channel = channelService.findById(id);
        if (null == channel) {
            return JsonData.buildFail("该通道已经不存在");
        }
        return JsonData.buildSuccess(channel);
    }

    @GetMapping("/api/v1/channel_all")
    public JsonData findAllChannel(){
        return JsonData.buildSuccess(channelService.findAllChannel());
    }
}
