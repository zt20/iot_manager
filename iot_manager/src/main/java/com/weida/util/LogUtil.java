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
package com.weida.util;

import com.weida.pojo.SystemUserLog;
import com.weida.service.SystemUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:15:47
 */
@Component
public class LogUtil {

      @Autowired
      SystemUserLogService systemUserLogService;


      /**
       * 操作日志存入到数据库
       *
       * @param request
       * @param instruction
       * @param operationType
       */
      public void addSystemUserLog(HttpServletRequest request, String instruction, String operationType) {
            SystemUserLog systemUserLog = new SystemUserLog();
            systemUserLog.setAccount(RequestUtil.getAccount(request));
            systemUserLog.setInstruction(instruction);
            systemUserLog.setOperationIp(RequestUtil.getClientIp(request));
            systemUserLog.setOperationTime(new Date());
            systemUserLog.setOperationType(operationType);
            systemUserLogService.addSystemUserLog(systemUserLog);
      }
}
