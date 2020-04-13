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
import com.weida.pojo.SystemUser;
import com.weida.service.SystemUserService;
import com.weida.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:11:12
 * 系统管理控制器
 */
@RestController
public class SystemUserController extends BaseController{

      @Autowired
      SystemUserService systemUserService;

      @PostMapping("/api/v1/systemUserInfoEdit_save")
      public JsonData updateSystemUser(SystemUser systemUser, HttpServletRequest request){
            String account = RequestUtil.getAccount(request);
            systemUser.setAccount(account);
            systemUserService.updateSystemUser(systemUser);
            showLog(request, "修改了个人信息", systemUser);
            logUtil.addSystemUserLog(request, "修改了个人信息", "修改");//记录日志到数据库
            //todo 记录
            return JsonData.buildSuccess("修改成功");
      }

      @PostMapping("/api/v1/systemUserInfo")
      public JsonData systemInfo(HttpServletRequest request){
            String account = RequestUtil.getAccount(request);
            SystemUser systemUser = systemUserService.findByAccount(account);
            systemUser.setPassword("*****");
            showLog(request, "查找个人信息", systemUser);
            return JsonData.buildSuccess(systemUser);
      }

      @PostMapping("/api/v1/systemUser_modify_password")
      public JsonData modifySystemUserPassword(HttpServletRequest request, String oldPassword, String newPassword){
            if (StringUtils.isBlank(oldPassword)&&StringUtils.isBlank(newPassword)){
                  return JsonData.buildFail("密码不能为空",null);
            }
            String account =  RequestUtil.getAccount(request);
            SystemUser systemUser = systemUserService.findByAccount(account);
            if (!systemUser.getPassword().equals(oldPassword)){
                 return JsonData.buildFail("旧密码不对",null);
            }
            if(systemUser.getPassword().equals(newPassword)){
                  return JsonData.buildFail("新旧密码一样不用修改",null);
            }
            systemUser.setPassword(StringUtils.trim(newPassword));
            systemUserService.updateSystemUser(systemUser);
            showLog(request,"修改密码", systemUser);
            logUtil.addSystemUserLog(request,"修改账号密码", "修改");
            return JsonData.buildSuccess();
      }

      @GetMapping("/api/v1/system_user_list")
      public PageInfo listSystemUser(int page){
         return systemUserService.findAll(page,10);
      }

      @PostMapping("/api/v1/systemUser_add")
      public JsonData addSystemUser(SystemUser systemUser, HttpServletRequest request){
            SystemUser user = systemUserService.findByAccount(systemUser.getAccount());
            if (null != user)
                  return JsonData.buildFail("账户已存在",null);
            systemUser.setAddTime(new Date());
            systemUserService.addSystemUser(systemUser);
            showLog(request, "添加系统用户", systemUser);
            logUtil.addSystemUserLog(request, "增加系统用户", "增加");
            return JsonData.buildSuccess();
      }

      @GetMapping("/api/v1/systemUser_delete")
      public JsonData deleteSystemUser(String account, HttpServletRequest request){
            if (account.equals(RequestUtil.getAccount(request))){
                  return JsonData.buildFail("不能删除当前使用的账户",null);
            }
            systemUserService.deleteSystemUser(account);
            showLog(request, "删除系统用户", account);
            logUtil.addSystemUserLog(request, "删除用户" + account, "删除");
            return JsonData.buildSuccess();
      }

      @PostMapping("/api/v1/systemUser_update")
      public JsonData updateSystemUserByAccount(SystemUser systemUser, HttpServletRequest request){
            System.out.println(systemUser.toString());
            String account = systemUser.getAccount();
            systemUserService.updateSystemUser(systemUser);
            showLog(request, "修改了" + account +"信息", systemUser);
            logUtil.addSystemUserLog(request, "修改了" + account +"信息", "修改");//记录日志到数据库
            //todo 记录
            return JsonData.buildSuccess("修改成功");

      }

      @GetMapping("/api/v1/systemUser_account")
      public JsonData findSystemUserByAccount(String account){
            SystemUser systemUser = systemUserService.findByAccount(account);
            return JsonData.buildSuccess(null, systemUser);
      }

}
