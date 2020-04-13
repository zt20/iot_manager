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
import com.weida.dao.ChannelAddPackageDao;
import com.weida.pojo.ChannelAddPackage;
import com.weida.service.ChannelAddPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/20
 * Time:11:09
 */
@Service
@Transactional
public class ChannelAddPackageServiceImpl implements ChannelAddPackageService {

    @Autowired
    ChannelAddPackageDao channelAddPackageDao;

    @Override
    public void addChannelAddPackage(ChannelAddPackage channelAddPackage) {
            channelAddPackageDao.addChannelAddPackage(channelAddPackage);
    }

    @Override
    public boolean isExistPackageId(ChannelAddPackage channelAddPackage) {
        return channelAddPackageDao.findByPackageId(channelAddPackage.getPackageId()) != null;
    }

    @Override
    public void updateChannelAddPackage(ChannelAddPackage channelAddPackage) {
        channelAddPackageDao.updateChannelPackage(channelAddPackage);
    }

    @Override
    public ChannelAddPackage findById(Integer id) {
        return channelAddPackageDao.findById(id);
    }

    @Override
    public PageInfo<ChannelAddPackage> findAddPackageList(int page, int pageSize, String channelName, String packageId, String showName, String originalName, Integer operator, Integer status) {
        PageHelper.startPage(page, pageSize, "id desc");
        List<ChannelAddPackage> channelAddPackages = channelAddPackageDao.findChannelAddPackage(channelName, packageId, showName, originalName, operator, status);
        PageInfo<ChannelAddPackage> pageInfo = new PageInfo<>(channelAddPackages);
        return pageInfo;
    }

    @Override
    public ChannelAddPackage findByPackageId(String packageId) {
        return channelAddPackageDao.findByPackageId(packageId);
    }

    @Override
    public List<String> findByChannelName(String channelName) {
       return channelAddPackageDao.findByChannelName(channelName);
    }
}
