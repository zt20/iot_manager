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
 * 代理商
 */
public class Agent {
    private Integer id;
    private String agentAccount;//登陆账户
    private String password;//密码
    private String name;//名字
    private String mobile;//手机号码
    private String email;//邮箱
    private String address;//地址
    private String serviceHotLine;//服务热线
    private String preSaleHotLine;//售前热线
    private Integer billingMode;//计费模式
    private Integer rebateMode;//返佣模式
    private Double rebateValue;//返佣比例
    private Double balance;//余额
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//需要设置时区 要不然时间不是东八区时间
    private Date addTime;//添加时间
    private Integer status;//状态
    private String companyName;//公司名称
    private String bankName;//开户行
    private String bankUserName;//开户名称
    private String bankAccount;//银行账户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceHotLine() {
        return serviceHotLine;
    }

    public void setServiceHotLine(String serviceHotLine) {
        this.serviceHotLine = serviceHotLine;
    }

    public String getPreSaleHotLine() {
        return preSaleHotLine;
    }

    public void setPreSaleHotLine(String preSaleHotLine) {
        this.preSaleHotLine = preSaleHotLine;
    }

    public Integer getBillingMode() {
        return billingMode;
    }

    public void setBillingMode(Integer billingMode) {
        if (billingMode == null) {
            this.billingMode = 0;
            return;
        }
        this.billingMode = billingMode;
    }

    public Integer getRebateMode() {
        return rebateMode;
    }

    public void setRebateMode(Integer rebateMode) {
        if (rebateMode == null) {
            this.rebateMode = 0;
            return;
        }
        this.rebateMode = rebateMode;
    }

    public Double getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(Double rebateValue) {
        if (rebateValue == null) {
            this.rebateValue = 0.00;
            return;
        }
        this.rebateValue = rebateValue;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        if (balance == null)
            this.balance = 0.00;
        this.balance = balance;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if (status == null) {
            this.status = 0;
            return;
        }
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
