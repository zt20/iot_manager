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

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/26
 * Time:11:45
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 客户
 */
public class Client {

      private int id; //id
      private String clientAccout; //登陆账户
      private String agentAccount;//所属代理商
      private String password; //密码
      private double balance;//余额
      @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",  timezone = "GMT+8")//需要设置时区 要不然时间不是东八区时间
      private Date addTime;//添加时间
      private int status;//状态

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getClientAccout() {
            return clientAccout;
      }

      public void setClientAccout(String clientAccout) {
            this.clientAccout = clientAccout;
      }

      public String getAgentAccount() {
            return agentAccount;
      }

      public void setAgentAccount(String agentAccount) {
            this.agentAccount = agentAccount;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public double getBalance() {
            return balance;
      }

      public void setBalance(double balance) {
            this.balance = balance;
      }

      public Date getAddTime() {
            return addTime;
      }

      public void setAddTime(Date addTime) {
            this.addTime = addTime;
      }

      public int getStatus() {
            return status;
      }

      public void setStatus(int status) {
            this.status = status;
      }

      @Override
      public String toString() {
            return ToStringBuilder.reflectionToString(this);
      }
}
