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
import com.weida.pojo.ChannelAddPackage;
import com.weida.service.ChannelAddPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/20
 * Time:11:05
 */
@RestController
public class ChannelAddPackageController extends BaseController{

    @Autowired
    ChannelAddPackageService channelAddPackageService;

    @PostMapping("/api/v1/channelAddPackage_add")
    public JsonData addChannelAddPackage(ChannelAddPackage channelAddPackage, HttpServletRequest request){
        if (channelAddPackageService.isExistPackageId(channelAddPackage)){
            return JsonData.buildFail("该加油包编码已经存在", null);
        }
        showLog(request, "添加了通道加油包", channelAddPackage);
        logUtil.addSystemUserLog(request, "添加了通道加油包", "增加");
        channelAddPackageService.addChannelAddPackage(channelAddPackage);
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/channelAddPackage_update")
    public JsonData updateChannelAddPackage(ChannelAddPackage channelAddPackage, HttpServletRequest request){
        showLog(request, "修改了通道加油包", channelAddPackage);
        logUtil.addSystemUserLog(request, "修改了通道加油包", "修改");
        channelAddPackageService.updateChannelAddPackage(channelAddPackage);
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/channelAddPackage_id")
    public JsonData findChannelAddPackageById(Integer id ){
        return JsonData.buildSuccess(channelAddPackageService.findById(id));
    }

    @GetMapping("/api/v1/channelAddPackage_list")
    public PageInfo<ChannelAddPackage> findChannelAddPackageList(int page, String channelName, String packageId, String showName, String originalName, Integer operator, Integer status){
        return channelAddPackageService.findAddPackageList(page, 20, channelName, packageId, showName, originalName, operator, status);
    }

    @GetMapping("/api/v1/channelAddPackage_channelName")
    public JsonData findByChannelName(String channelName){
        return JsonData.buildSuccess(channelAddPackageService.findByChannelName(channelName));
    }



}
