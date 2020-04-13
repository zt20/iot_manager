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
import com.weida.dao.ChannelTariffDao;
import com.weida.pojo.ChannelTariff;
import com.weida.service.ChannelTariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/18
 * Time:14:15
 */
@Service
@Transactional
public class ChannelTariffServiceImpl implements ChannelTariffService {

    @Autowired
    ChannelTariffDao channelTariffDao;

    @Override
    public void addChannelTariff(ChannelTariff channelTariff) {
        channelTariffDao.addChannelTariff(channelTariff);
    }

    @Override
    public void updateChannelTariff(ChannelTariff channelTariff) {
        channelTariffDao.updateChannelTariff(channelTariff);
    }

    @Override
    public PageInfo<ChannelTariff> channelTariffList(int page, int pageSize, String channelName, String packageId, String showName, String originalName, Integer operator) {
        PageHelper.startPage(page, pageSize, "id desc");
        List<ChannelTariff> channelTariffs = channelTariffDao.findChannelTariff(channelName, packageId, showName, originalName, operator);
        PageInfo<ChannelTariff> pageInfo = new PageInfo<>(channelTariffs);
        return pageInfo;
    }

    @Override
    public ChannelTariff findById(Integer id) {
        return channelTariffDao.findById(id);
    }

    @Override
    public boolean isExistPackageId(ChannelTariff channelTariff) {
        return channelTariffDao.findByPackageId(channelTariff.getPackageId()) != null;
    }

    @Override
    public List<String> findByChannelName(String channelName) {
        return channelTariffDao.findByChannelName(channelName);
    }

    @Override
    public boolean channelExistPackageId(String channelName, String packageId) {
        return channelTariffDao.channelExistPackageId(channelName, packageId) != null;
    }

    @Override
    public ChannelTariff findByPackageId(String packageId) {
       return channelTariffDao.findByPackageId(packageId);
    }
}
