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

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/14
 * Time:10:28
 */
public class Channel implements Serializable {

    private Integer id; //主键id
    private String channelName; //通道名称
    private Double channelDiscount;//通道折扣
    private String showName; //展示名称
    private Double channelAgentDiscount; //代理商基础折扣
    private Integer operator; //运营商类型
    private Integer submissionInterval; //提交时间间隔
    private Integer taskScanInterval;// 任务扫描时间间隔
    private Integer settlementDate; //结算日
    private String note;//备注
    private Integer status; //状态
    private Integer rechargeMode; // 充值模式
    private Integer sms; //是否启动短信

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Double getChannelDiscount() {
        return channelDiscount;
    }

    public void setChannelDiscount(Double channelDiscount) {
        this.channelDiscount = channelDiscount;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Double getChannelAgentDiscount() {
        return channelAgentDiscount;
    }

    public void setChannelAgentDiscount(Double channelAgentDiscount) {
        this.channelAgentDiscount = channelAgentDiscount;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getSubmissionInterval() {
        return submissionInterval;
    }

    public void setSubmissionInterval(Integer submissionInterval) {
        this.submissionInterval = submissionInterval;
    }

    public Integer getTaskScanInterval() {
        return taskScanInterval;
    }

    public void setTaskScanInterval(Integer taskScanInterval) {
        this.taskScanInterval = taskScanInterval;
    }

    public Integer getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Integer settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRechargeMode() {
        return rechargeMode;
    }

    public void setRechargeMode(Integer rechargeMode) {
        this.rechargeMode = rechargeMode;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
