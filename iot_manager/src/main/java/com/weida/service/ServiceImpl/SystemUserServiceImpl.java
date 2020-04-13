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
import com.weida.dao.SystemUserDao;
import com.weida.pojo.SystemUser;
import com.weida.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:11:16
 */

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

      @Autowired
      SystemUserDao systemUserDao;

      /**
       * 添加系统管理员
       * @param systemUser
       */
      @Override
      public void addSystemUser(SystemUser systemUser) {
            systemUserDao.addSystemUser(systemUser);
      }

      /**
       * 更新系统管理员
       * @param systemUser
       */
      @Override
      public void updateSystemUser(SystemUser systemUser) {
            systemUserDao.updateSystemUser(systemUser);
      }

      /**
       * 删除系统管理员
       * @param account
       */
      @Override
      public void deleteSystemUser(String account) {
            systemUserDao.deleteSystemUser(account);
      }

      /**
       * 通过登录账号查找系统管理员
       * @param account
       * @return
       */
      @Override
      public SystemUser findByAccount(String account) {
            return systemUserDao.findByAccount(account);
      }

      /**
       * 系统管理员分页业务处理
       * @param pageNum
       * @param pageSize
       * @return
       */
      @Override
      public PageInfo<SystemUser> findAll(int pageNum, int pageSize) {
            PageHelper.startPage(pageNum,pageSize, "addTime desc");
            List<SystemUser> ListSystemUser = systemUserDao.findAll();
            PageInfo<SystemUser> page = new PageInfo<>(ListSystemUser);
            return page;
      }
}
