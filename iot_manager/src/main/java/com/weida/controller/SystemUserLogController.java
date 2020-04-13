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
import com.weida.service.SystemUserLogService;
import com.weida.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:10:57
 * 系统日志的控制器
 */
@RestController
public class SystemUserLogController extends BaseController {

      @Autowired
      SystemUserLogService systemUserLogService;

//      @PostMapping("/systemUser_log")
//      public JsonData getSystemUserLog(HttpServletRequest request, String startTime, String endTime, String optType, String Text1){
//            String account = RequestUtil.getAccount(request);
//            showLog(request,"查看日志",optType + Text1 + startTime + endTime );
//            List<SystemUserLog> systemUserLogs = systemUserLogService.findByAccount(account, startTime, endTime, optType, Text1);
////            systemUserLogs.forEach(System.out::println);
//            return JsonData.buildSuccess(systemUserLogs);
//      }

      @GetMapping("/api/v1/systemUser_log")
      public PageInfo getSystemUserLog(int page,HttpServletRequest request, String startTime, String endTime, String optType, String Text1) {
            String account = RequestUtil.getAccount(request);
//            System.out.println("---" + startTime + "-" + endTime + "-" + optType + "-" + Text1);
            return systemUserLogService.findByAccount(page, 5, account, startTime, endTime, optType, Text1);
      }

      @GetMapping("/api/v1/systemUser_all_log")
      public PageInfo getSystemUserAllLog(int page, String account, String startTime, String endTime, String optType, String Text1){
            return systemUserLogService.findByAccount(page, 10,  account, startTime, endTime, optType, Text1);
      }


}
