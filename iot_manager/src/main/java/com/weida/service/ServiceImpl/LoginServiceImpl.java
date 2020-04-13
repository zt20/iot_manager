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

import com.weida.dao.LoginDao;
import com.weida.pojo.SystemUser;
import com.weida.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:17:40
 */
@Service
public class LoginServiceImpl implements LoginService {

      @Autowired
      LoginDao loginDao;

      /**
       * 登录时候验证
       * @param systemUser
       * @return
       */
      @Override
      public SystemUser findByNameAndPassword(SystemUser systemUser) {
            return loginDao.findByNameAndPasswd(systemUser);
      }
}
