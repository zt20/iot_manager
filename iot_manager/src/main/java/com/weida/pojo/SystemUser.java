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
package com.weida.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:17:04
 */
public class SystemUser {

      private int id;//主键id

      private String account;//登陆名称

      private String password;//密码

      private int type;//账号类型

      private String name;//用户姓名

      private String mobile;//手机号码

      private String email;//邮箱地址

      private int status;//用户状态

      @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",  timezone = "GMT+8")//需要设置时区 要不然时间不是东八区时间
      private Date addTime;//用户添加的时间

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getAccount() {
            return account;
      }

      public void setAccount(String account) {
            this.account = account;
      }

      public int getType() {
            return type;
      }

      public void setType(int type) {
            this.type = type;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getMobile() {
            return mobile;
      }

      public void setMobile(String mobile) {
            this.mobile = mobile;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public int getStatus() {
            return status;
      }

      public void setStatus(int status) {
            this.status = status;
      }

      public Date getAddTime() {
            return addTime;
      }

      public void setAddTime(Date addTime) {
            this.addTime = addTime;
      }

      @Override
      public String toString() {
            return ToStringBuilder.reflectionToString(this);
      }
}
