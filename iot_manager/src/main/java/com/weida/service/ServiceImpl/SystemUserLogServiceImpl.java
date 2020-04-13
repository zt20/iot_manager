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
import com.weida.dao.SystemUserLogDao;
import com.weida.pojo.SystemUserLog;
import com.weida.service.SystemUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:10:54
 */
@Service
@Transactional
public class SystemUserLogServiceImpl implements SystemUserLogService {

      @Autowired
      SystemUserLogDao systemUserLogDao;

      /**
       * 通过输入条件删选管理员操作日志
       * @param account
       * @param startTime
       * @param endTime
       * @param optType
       * @param Text1
       * @return
       */
      @Override
      public List<SystemUserLog> findByAccount(String account,  String startTime, String endTime, String optType, String Text1) {
            return systemUserLogDao.findByAccount(account, startTime, endTime, optType, Text1);
      }

      /**
       * 添加管理员操作日志
       * @param systemUserLog
       */
      @Override
      public void addSystemUserLog(SystemUserLog systemUserLog) {
            systemUserLogDao.addSystemUserLog(systemUserLog);
      }

      /**
       * 分页逻辑处理
       * @param pageNum
       * @param pageSize
       * @param account
       * @param startTime
       * @param endTime
       * @param optType
       * @param Text1
       * @return
       */
      @Override
      public PageInfo<SystemUserLog> findByAccount(int pageNum, int pageSize, String account, String startTime, String endTime, String optType, String Text1) {
            PageHelper.startPage(pageNum,pageSize, "operationTime desc");
            List<SystemUserLog> systemUserLogs = systemUserLogDao.findByAccount(account, startTime, endTime, optType, Text1);
            PageInfo<SystemUserLog> page = new PageInfo<>(systemUserLogs);
            return page;
      }

}
