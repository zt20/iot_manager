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

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:16:43
 */
public class RequestUtil {

      /**
       * 获取真实IP
       * @param request
       * @return
       */
      public static String getClientIp(HttpServletRequest request) { //X-Forwarded-For，不区分大小写
            String possibleIpStr = request.getHeader("X-Forwarded-For");
            String remoteIp = request.getRemoteAddr();
            String clientIp;
            if (StringUtils.isNotBlank(possibleIpStr) && !"unknown".equalsIgnoreCase(possibleIpStr)) {
                  //可能经过好几个转发流程，第一个是用户的真实ip，后面的是转发服务器的ip
                  clientIp = possibleIpStr.split(",")[0].trim();
            } else { //如果转发头ip为空，说明是直接访问的，没有经过转发
                  clientIp = remoteIp;
            }
            return clientIp;
      }


      /**
       * 获取登录名在session中
       * @param request
       * @return
       */
      public static String getAccount(HttpServletRequest request){
            HttpSession session = request.getSession();
            return (String) session.getAttribute("account");
      }

}
