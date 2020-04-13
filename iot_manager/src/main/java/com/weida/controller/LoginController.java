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

import com.weida.domain.JsonData;
import com.weida.pojo.SystemUser;
import com.weida.service.LoginService;
import com.weida.service.SystemUserService;
import com.weida.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:17:03
 * 登录控制器
 */
@Controller
public class LoginController extends BaseController{

      @Autowired
      LoginService loginService;

      @Autowired
      SystemUserService systemUserService;


      @PostMapping("/api/v1/system_user_login")
      @ResponseBody
      public JsonData login(SystemUser systemUser, HttpServletRequest request){

            if (!CodeUtil.checkVerifyCode(request)) {
                  return JsonData.buildFail("验证码错误",null);
            }
//            systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
            SystemUser user = loginService.findByNameAndPassword(systemUser);
            HttpSession session = request.getSession();
            if (null != user){
                  session.setAttribute("account", user.getAccount());
                  showLog(request, "登陆成功", user);
                  logUtil.addSystemUserLog(request, "登陆服务器", "登陆");//记录日志到数据库
//                  return "redirect:/default.do";
                  return JsonData.buildSuccess();

            }else {
//                  showLog(request, "登陆失败", null);
//                  return "账户或者密码错误";
//                  return "redirect:/login.do";
                  return JsonData.buildFail("账户密码错误", null);
            }
      }


      @GetMapping("/login")
      public ModelAndView relogin(HttpServletRequest request){
            ModelAndView modelAndView = new ModelAndView("/Login.htm");
            return modelAndView;
      }

      @GetMapping("/default")
      public ModelAndView defaultView (){
            ModelAndView modelAndView = new ModelAndView("/Default.htm");
            return modelAndView;
      }

      @GetMapping("/api/v1/getAccount")
      @ResponseBody
      public JsonData getAccount(HttpSession session){
            String account =  session.getAttribute("account").toString();
            if (null == account)
                  account = "";
            return JsonData.buildSuccess(account, null);
      }


}
