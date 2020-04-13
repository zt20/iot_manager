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
import com.weida.dao.IotCardDao;
import com.weida.pojo.IotCard;
import com.weida.service.IotCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/26
 * Time:10:59
 */
@Service
@Transactional
public class IotCardServiceImpl implements IotCardService {

    @Autowired
    IotCardDao iotCardDao;

    @Override
    public void addIotCard(IotCard iotCard) {
        iotCard.setAddTime(new Date());
        iotCardDao.addIotCard(iotCard);
    }

    @Override
    public void addIotCards(List<IotCard> iotCards) {
        for (IotCard iotcard : iotCards) {
            this.addIotCard(iotcard);
        }
    }

    @Override
    public void deleteByIotCards(List<String> iotCards) {
        for (String iotCard : iotCards) {
            this.deleteByIotCard(iotCard);
        }
    }

    @Override
    public void deleteByIotCard(String iotCard) {
        if (this.findByIotCard(iotCard) == null)
            return;
        iotCardDao.deleteByIotCard(iotCard);
    }

    @Override
    public IotCard findByIotCard(String iotCard) {
        return iotCardDao.findByIotCard(iotCard);
    }

    @Override
    public PageInfo<IotCard> findByChooseList(int page, int pageSize, String startCardId, String endCardId, String cardId, Integer operator, Integer status, String channelName, String agentName, Integer trafficPool, Integer networkStatus) {
        PageHelper.startPage(page, pageSize, "id desc");
        List<IotCard> iotCards = iotCardDao.findByChooseList(startCardId, endCardId, cardId, operator, status, channelName, agentName, trafficPool, networkStatus);
        PageInfo pageInfo = new PageInfo(iotCards);
        return pageInfo;
    }

    @Override
    public void updateTrafficUse(Map<String, List<String>> useTraffic) {
        for (Map.Entry<String, List<String>> entry : useTraffic.entrySet()) {
            String cardId = entry.getKey();
            List<String> useSize = entry.getValue();
            if (findByIotCard(cardId) == null)
                return;
            iotCardDao.updateTrafficUse(cardId, useSize.get(0), useSize.get(1));
        }
    }

    @Override
    public List<IotCard> findIotCards(String startCardId, String endCardId, String cardId, Integer operator, Integer status, String agentAccount, String channelName) {
        return iotCardDao.findByChooseList(startCardId, endCardId, cardId, operator , status, channelName, null, null, null);
    }

    @Override
    public void updateAgent(int id, String agentAccount) {
        iotCardDao.updateAgent(id, agentAccount);
    }

    @Override
    public void updateStatus(int id, Integer status) {
        iotCardDao.updateStatus(id, status);
    }

    @Override
    public void updateNetwork(int id, Integer networkStatus) {
        iotCardDao.updateNetwork(id, networkStatus);
    }

    @Override
    public void updateCardType(int id, Integer cardType) {
        iotCardDao.updateCardType(id, cardType);
    }

    @Override
    public void updateTrafficPool(int id, Integer trafficPool) {
        iotCardDao.updateTrafficPool(id, trafficPool);
    }
}
