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
package com.weida.config;

import com.weida.util.RequestUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:19:33
 */

public class LoginInterceptor implements HandlerInterceptor {


      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            System.out.println("--------------------------" + request.getRequestURI());

            if (request.getRequestURI().equals("/api/v1/system_user_login.do") || request.getRequestURI().equals("/api/v1/login.do")
                  || request.getRequestURI().equals("/api/v1/kaptcha.do")) {
                  return true;
            }

//          if (request.getRequestURI().equals("/login.do")){
//                response.sendRedirect("/Login.htm");
//                return false;
//          }


            String account = RequestUtil.getAccount(request);
            if (null == account) {
                  response.sendRedirect("/Login.htm");
                  return false;
            }

            return true;
      }

}
