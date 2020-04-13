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
 * Date:2019/3/21
 * Time:13:36
 */
public class AgentTariff implements Serializable {

    private Integer id;//主键
    private String agentName;//代理商名称
    private String channelName;//供应商名称
    private String packageId;//套餐编码
    private String showName;//展示名称
    private Integer packageSize;//包大小
    private Integer operator;//运营商
    private Integer feeMode;//计费模式
    private Integer superpositionMode;//叠加模式
    private Integer rebateMode;//返佣模式
    private Integer totalStatus;//总状态
    private Integer status;//状态
    private Double originalPrice;//原始价格
    private Double tariffPrice;//售价
    private Double tariffCost;//成本

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(Integer feeMode) {
        this.feeMode = feeMode;
    }

    public Integer getSuperpositionMode() {
        return superpositionMode;
    }

    public void setSuperpositionMode(Integer superpositionMode) {
        this.superpositionMode = superpositionMode;
    }

    public Integer getRebateMode() {
        return rebateMode;
    }

    public void setRebateMode(Integer rebateMode) {
        this.rebateMode = rebateMode;
    }

    public Integer getTotalStatus() {
        return totalStatus;
    }

    public void setTotalStatus(Integer totalStatus) {
        this.totalStatus = totalStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(Double tariffPrice) {
        this.tariffPrice = tariffPrice;
    }

    public Double getTariffCost() {
        return tariffCost;
    }

    public void setTariffCost(Double tariffCost) {
        this.tariffCost = tariffCost;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
