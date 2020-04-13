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
 * Date:2019/2/20
 * Time:10:38
 */
public class SystemUserLog {

      private int id; //主键id
      private String account;//账号
      private String operationType;//操作类型
      private String operationIp;//操作IP
      @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",  timezone = "GMT+8")//需要设置时区 要不然时间不是东八区时间
      private Date operationTime;//操作时间
      private String instruction;//说明

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

      public String getOperationType() {
            return operationType;
      }

      public void setOperationType(String operationType) {
            this.operationType = operationType;
      }

      public String getOperationIp() {
            return operationIp;
      }

      public void setOperationIp(String operationIp) {
            this.operationIp = operationIp;
      }

      public Date getOperationTime() {
            return operationTime;
      }

      public void setOperationTime(Date operationTime) {
            this.operationTime = operationTime;
      }

      public String getInstruction() {
            return instruction;
      }

      public void setInstruction(String instruction) {
            this.instruction = instruction;
      }

      @Override
      public String toString() {
            return ToStringBuilder.reflectionToString(this);
      }
}
