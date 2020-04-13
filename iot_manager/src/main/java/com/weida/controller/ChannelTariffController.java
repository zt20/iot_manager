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
import com.weida.pojo.ChannelTariff;
import com.weida.service.ChannelTariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/18
 * Time:14:18
 */
@RestController
public class ChannelTariffController extends BaseController {

    @Autowired
    ChannelTariffService channelTariffService;

    @PostMapping("/api/v1/channelTariff_add")
    public JsonData addChannelTariff(ChannelTariff channelTariff, HttpServletRequest request) {
        showLog(request, "添加了通道资费计划", channelTariff);
        logUtil.addSystemUserLog(request, "添加了通道资费计划", "增加");
        if (channelTariffService.isExistPackageId(channelTariff)) {
            return JsonData.buildFail("该套餐编码已经存在", null);
        }
        channelTariffService.addChannelTariff(channelTariff);
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/channelTariff_update")
    public JsonData updateChannelTariff(ChannelTariff channelTariff, HttpServletRequest request) {
        showLog(request, "修改了通道资费计划", channelTariff);
        logUtil.addSystemUserLog(request, "修改了通道资费计划", "修改");
        channelTariffService.updateChannelTariff(channelTariff);
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/channelTariff_list")
    public PageInfo<ChannelTariff> channelTariffList(int page, String channelName, String packageId, String showName, String originalName, Integer operator) {
        return channelTariffService.channelTariffList(page, 20, channelName, packageId, showName, originalName, operator);
    }

    @GetMapping("/api/v1/channelTariff_id")
    public JsonData findById(Integer id) {
        return JsonData.buildSuccess(channelTariffService.findById(id));
    }

    @GetMapping("/api/v1/channelTariff_channelName")
    public JsonData findByChannelName(String channelName){
        return JsonData.buildSuccess(channelTariffService.findByChannelName(channelName));
    }

}
