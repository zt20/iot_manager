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
package com.weida.dao;

import com.weida.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;


/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:14:46
 * 该类是动态Sql生成类
 */
public class ProviderSql {

    /**
     * 根据条件来生成sql
     *
     * @param account   账号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param optType   类型
     * @param Text1     操作类型
     * @return sql
     */
    public String querySystemUserLogSql(String account, String startTime, String endTime, String optType, String Text1) {
        SQL sql = new SQL().SELECT("*").FROM("iot_system_user_log");
        if (StringUtils.isNotBlank(account)) {
            sql.WHERE("account = #{account}");
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.WHERE("operationTime between #{startTime} and #{endTime}");
        }
        if (StringUtils.isNotBlank(optType)) {
            sql.WHERE("operationType = #{optType}");
        }

        if (StringUtils.isNotBlank(Text1)) {
            sql.WHERE("instruction like '%" + Text1 + "%'");
        }

        return sql.toString();
    }

    /**
     * 更新系统管理员的sql
     *
     * @param systemUser 系统管理员
     * @return sql
     */
    public String updateSystemUserSql(SystemUser systemUser) {
        SQL sql = new SQL().UPDATE("iot_system_user");
        if (StringUtils.isNotBlank(systemUser.getPassword())) {
            sql.SET("password = #{password}");
        }
        if (StringUtils.isNotBlank(systemUser.getEmail())) {
            sql.SET("email = #{email}");
        }
        if (StringUtils.isNotBlank(systemUser.getMobile())) {
            sql.SET("mobile = #{mobile}");
        }
        if (StringUtils.isNotBlank(systemUser.getName())) {
            sql.SET("name = #{name}");
        }
        sql.SET("status = #{status}");
        sql.WHERE("account = #{account}");
        return sql.toString();
    }

    /**
     * 添加代理商的sql
     *
     * @param agent 代理商
     * @return sql
     */
    public String addAgentSql(Agent agent) {
        SQL sql = new SQL().INSERT_INTO("iot_agents");
        if (agent.getStatus() == null) {
            agent.setStatus(0);
        }
        if (agent.getBillingMode() == null) {
            agent.setBillingMode(0);
        }
        if (agent.getRebateValue() == null) {
            agent.setRebateValue(0.0000);
        }
        if (agent.getBalance() == null) {
            agent.setBalance(0.0000);
        }
        if (agent.getRebateMode() == null) {
            agent.setRebateMode(0);
        }
        sql.VALUES("agentAccount", "#{agentAccount}");
        sql.VALUES("password", "#{password}");
        sql.VALUES("addTime", "#{addTime}");
        sql.VALUES("billingMode", "#{billingMode}");
        sql.VALUES("rebateMode", "#{rebateMode}");
        sql.VALUES("rebateValue", "#{rebateValue}");
        sql.VALUES("balance", "#{balance}");
        sql.VALUES("status", "#{status}");
        if (StringUtils.isNotBlank(agent.getAddress())) {
            sql.VALUES("address", "#{address}");
        }
        if (StringUtils.isNotBlank(agent.getMobile())) {
            sql.VALUES("mobile", "#{mobile}");
        }
        if (StringUtils.isNotBlank(agent.getName())) {
            sql.VALUES("name", "#{name}");
        }
        if (StringUtils.isNotBlank(agent.getEmail())) {
            sql.VALUES("email", "#{email}");
        }
        if (StringUtils.isNotBlank(agent.getCompanyName())) {
            sql.VALUES("companyName", "#{companyName}");
        }
        if (StringUtils.isNotBlank(agent.getBankAccount())) {
            sql.VALUES("bankAccount", "#{bankAccount}");
        }
        if (StringUtils.isNotBlank(agent.getBankName())) {
            sql.VALUES("bankName", "#{bankName}");
        }
        if (StringUtils.isNotBlank(agent.getBankUserName())) {
            sql.VALUES("bankUserName", "#{bankUserName}");
        }
        if (StringUtils.isNotBlank(agent.getServiceHotLine())) {
            sql.VALUES("serviceHotLine", "#{serviceHotLine}");
        }
        if (StringUtils.isNotBlank(agent.getPreSaleHotLine())) {
            sql.VALUES("preSaleHotLine", "#{preSaleHotLine}");
        }
        return sql.toString();
    }

    /**
     * 更新代理商的sql
     *
     * @param agent 代理商
     * @return sql
     */
    public String updateAgentSql(Agent agent) {
        SQL sql = new SQL().UPDATE("iot_agents");
        sql.SET("billingMode = #{billingMode}");
        sql.SET("status = #{status}");
        sql.SET("rebateValue = #{rebateValue}");
        if (StringUtils.isNotBlank(agent.getAddress())) {
            sql.SET("address = #{address}");
        }
        if (StringUtils.isNotBlank(agent.getMobile())) {
            sql.SET("mobile = #{mobile}");
        }
        if (StringUtils.isNotBlank(agent.getName())) {
            sql.SET("name = #{name}");
        }
        if (StringUtils.isNotBlank(agent.getEmail())) {
            sql.SET("email = #{email}");
        }
        if (StringUtils.isNotBlank(agent.getCompanyName())) {
            sql.SET("companyName = #{companyName}");
        }
        if (StringUtils.isNotBlank(agent.getBankAccount())) {
            sql.SET("bankAccount = #{bankAccount}");
        }
        if (StringUtils.isNotBlank(agent.getBankName())) {
            sql.SET("bankName = #{bankName}");
        }
        if (StringUtils.isNotBlank(agent.getBankUserName())) {
            sql.SET("bankUserName = #{bankUserName}");
        }
        if (StringUtils.isNotBlank(agent.getServiceHotLine())) {
            sql.SET("serviceHotLine = #{serviceHotLine}");
        }
        if (StringUtils.isNotBlank(agent.getPreSaleHotLine())) {
            sql.SET("preSaleHotLine = #{preSaleHotLine}");
        }
        if (StringUtils.isNotBlank(agent.getAgentAccount())) {
            sql.SET("agentAccount = #{agentAccount}");
        }
        if (StringUtils.isNotBlank(agent.getPassword())) {
            sql.SET("password = #{password}");
        }
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * 根据条件动态生成sql
     *
     * @param id           id
     * @param agentAccount 代理商账号
     * @param name         名称
     * @param status       状态
     * @return sql
     */
    public String selectAgentSql(int id, String agentAccount, String name, int status) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_agents");
        if (id > 0) {
            sql.WHERE("id = #{id}");
        }
        if (StringUtils.isNotBlank(agentAccount)) {
            sql.WHERE("agentAccount = #{agentAccount}");
        }
        if (status > 0) {
            sql.WHERE("status = #{status}");
        }
        if (StringUtils.isNotBlank(name)) {
            sql.WHERE("name = #{name}");
        }
        return sql.toString();
    }


    /**
     * 添加通道sql
     *
     * @param channel 通道pojo类
     * @return sql
     */
    public String addChannelSql(Channel channel) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_channel");
        if (channel.getStatus() == null) {
            channel.setStatus(0);
        }
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("channelDiscount", "#{channelDiscount}");
        sql.VALUES("showName", "#{showName}");
        sql.VALUES("channelAgentDiscount", "#{channelAgentDiscount}");
        sql.VALUES("operator", "#{operator}");
        sql.VALUES("submissionInterval", "#{submissionInterval}");
        sql.VALUES("taskScanInterval", "#{taskScanInterval}");
        sql.VALUES("settlementDate", "#{settlementDate}");
        sql.VALUES("note", "#{note}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("rechargeMode", "#{rechargeMode}");
        sql.VALUES("sms", "#{sms}");
        return sql.toString();
    }

    /**
     * 更新通道的sql
     *
     * @param channel 通道pojo类
     * @return sql
     */
    public String updateChannel(Channel channel) {
        SQL sql = new SQL();
        sql.UPDATE("iot_channel");
        sql.SET("channelName=#{channelName}");
        sql.SET("channelDiscount=#{channelDiscount}");
        sql.SET("showName=#{showName}");
        sql.SET("channelAgentDiscount=#{channelAgentDiscount}");
        sql.SET("operator=#{operator}");
        sql.SET("submissionInterval= #{submissionInterval}");
        sql.SET("taskScanInterval=#{taskScanInterval}");
        sql.SET("settlementDate=#{settlementDate}");
        sql.SET("note=#{note}");
        sql.SET("rechargeMode=#{rechargeMode}");
        sql.SET("sms=#{sms}");
        sql.WHERE("id = #{id}");
        return sql.toString();
    }


    /**
     * 通过通道名称状态或者运营商来查找通道
     *
     * @param channelName 通道名称
     * @param status      状态
     * @param operator    运营商
     * @return sql
     */
    public String findChannelList(String channelName, Integer status, Integer operator) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_channel");
        if (StringUtils.isNotBlank(channelName)) {
            sql.WHERE("channelName = #{channelName}");
        }
        if (status != null) {
            sql.WHERE("status = #{status}");
        }
        if (operator != null) {
            sql.WHERE("operator = #{operator}");
        }
        return sql.toString();
    }

    /**
     * 添加通道资费计划
     *
     * @param channelTariff 通道资费计划pojo类
     * @return sql
     */
    public String addChannelTariff(ChannelTariff channelTariff) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_channel_tariff");
        sql.VALUES("packageId", "#{packageId}");
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("originalName", "#{originalName}");
        sql.VALUES("operator", "#{operator}");
        sql.VALUES("showName", "#{showName}");
        sql.VALUES("originalPrice", "#{originalPrice}");
        sql.VALUES("tariffPrice", "#{tariffPrice}");
        sql.VALUES("tariffCost", "#{tariffCost}");
        sql.VALUES("trafficType", "#{trafficType}");
        sql.VALUES("packageSize", "#{packageSize}");
        sql.VALUES("feeMode", "#{feeMode}");
        sql.VALUES("superpositionMode", "#{superpositionMode}");
        sql.VALUES("remark", "#{remark}");
        sql.VALUES("status", "#{status}");
        return sql.toString();
    }

    /**
     * 更新通道资费计划
     *
     * @param channelTariff 通道资费计划pojo类
     * @return sql
     */
    public String updateChannelTariff(ChannelTariff channelTariff) {
        SQL sql = new SQL();
        sql.UPDATE("iot_channel_tariff");
        sql.SET("showName = #{showName}");
        sql.SET("originalPrice = #{originalPrice}");
        sql.SET("tariffPrice = #{tariffPrice}");
        sql.SET("tariffCost = #{tariffCost}");
        sql.SET("trafficType = #{trafficType}");
        sql.SET("packageSize = #{packageSize}");
        sql.SET("feeMode = #{feeMode}");
        sql.SET("superpositionMode = #{superpositionMode}");
        sql.SET("remark = #{remark}");
        sql.SET("status = #{status}");
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * 生成查询通道资费计划的sql
     *
     * @param channelName  通道名称
     * @param packageId    套餐编码
     * @param showName     展示名称
     * @param originalName 原始名称
     * @param operator     运营商
     * @return sql
     */
    public String findChannelTariff(String channelName, String packageId, String showName, String originalName, Integer operator) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_channel_tariff").WHERE("1 = 1");
        if (StringUtils.isNotBlank(channelName)) {
            sql.WHERE("channelName = #{channelName}");
        }
        if (StringUtils.isNotBlank(packageId)) {
            sql.WHERE("packageId = #{packageId}");
        }
        if (StringUtils.isNotBlank(showName)) {
            sql.WHERE("showName = #{showName}");
        }
        if (StringUtils.isNotBlank(originalName)) {
            sql.WHERE("originalName = #{originalName}");
        }
        if (operator != null) {
            sql.WHERE("operator = #{operator}");
        }
        return sql.toString();
    }

    /**
     * 生成添加通道加油包的sql
     *
     * @param channelAddPackage 通道加油包pojo类
     * @return 返回sql
     */
    public String addChannelAddPackage(ChannelAddPackage channelAddPackage) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_channel_addPackage");
        sql.VALUES("packageId", "#{packageId}");
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("operator", "#{operator}");
        sql.VALUES("originalName", "#{originalName}");
        sql.VALUES("showName", "#{showName}");
        sql.VALUES("originalPrice", "#{originalPrice}");
        sql.VALUES("packagePrice", "#{packagePrice}");
        sql.VALUES("packageCost", "#{packageCost}");
        sql.VALUES("packageSize", "#{packageSize}");
        sql.VALUES("feeMode", "#{feeMode}");
        sql.VALUES("remark", "#{remark}");
        sql.VALUES("status", "#{status}");
        return sql.toString();
    }

    /**
     * 生成更新通道加油包的sql
     *
     * @param channelAddPackage 通道加油包pojo类
     * @return 返回sql
     */
    public String updateChannelAddPackage(ChannelAddPackage channelAddPackage) {
        SQL sql = new SQL();
        sql.UPDATE("iot_channel_addPackage");
        sql.SET("showName = #{showName}");
        sql.SET("originalPrice = #{originalPrice}");
        sql.SET("packagePrice = #{packagePrice}");
        sql.SET("packageCost = #{packageCost}");
        sql.SET("packageSize = #{packageSize}");
        sql.SET("feeMode = #{feeMode}");
        sql.SET("remark = #{remark}");
        sql.SET("status = #{status}");
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * 返回查找通道加油包的sql
     *
     * @param channelName  所属通道的名称
     * @param packageId    套餐包编码
     * @param showName     展示名称
     * @param originalName 原始名称
     * @param operator     运营商
     * @param status       状态
     * @return sql
     */
    public String findChannelAddPackage(String channelName, String packageId, String showName, String originalName, Integer operator, Integer status) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_channel_addPackage").WHERE("1 = 1");
        if (status != null) {
            sql.WHERE("status = #{status}");
        }
        if (operator != null) {
            sql.WHERE("operator = #{operator}");
        }
        if (StringUtils.isNotBlank(showName)) {
            sql.WHERE("showName = #{showName}");
        }
        if (StringUtils.isNotBlank(channelName)) {
            sql.WHERE("channelName = #{channelName}");
        }
        if (StringUtils.isNotBlank(packageId)) {
            sql.WHERE("packageId = #{packageId}");
        }
        if (StringUtils.isNotBlank(originalName)) {
            sql.WHERE("originalName = #{originalName}");
        }
        return sql.toString();
    }

    /**
     * 生成添加代理商资费计划sql
     *
     * @param agentTariff 代理商资费pojo类
     * @return sql
     */
    public String addAgentTariff(AgentTariff agentTariff) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_agent_tariff");
        sql.VALUES("agentName", "#{agentName}");
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("packageId", "#{packageId}");
        sql.VALUES("showName", "#{showName}");
        sql.VALUES("packageSize", "#{packageSize}");
        sql.VALUES("operator", "#{operator}");
        sql.VALUES("feeMode", "#{feeMode}");
        sql.VALUES("superpositionMode", "#{superpositionMode}");
        sql.VALUES("rebateMode", "#{rebateMode}");
        sql.VALUES("totalStatus", "#{totalStatus}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("originalPrice", "#{originalPrice}");
        sql.VALUES("tariffPrice", "#{tariffPrice}");
        sql.VALUES("tariffCost", "#{tariffCost}");
        return sql.toString();
    }

    /**
     * 生成查找代理商资费计划sql
     *
     * @param operator          运营商
     * @param channelName       通道名称
     * @param showName          展示名称
     * @param agentName         代理名称
     * @param packageId         套餐包编码
     * @param status            状态
     * @param feeMode           计费模式
     * @param superpositionMode 叠加模式
     * @return sql
     */
    public String findAgentTariff(Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode, Integer superpositionMode) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_agent_tariff");
        if (operator != null) {
            sql.WHERE("operator = #{operator}");
        }
        if (StringUtils.isNotBlank(channelName)) {
            sql.WHERE("channelName = #{channelName}");
        }
        if (StringUtils.isNotBlank(showName)) {
            sql.WHERE("showName = #{showName}");
        }
        if (StringUtils.isNotBlank(agentName)) {
            sql.WHERE("agentName = #{agentName}");
        }
        if (StringUtils.isNotBlank(packageId)) {
            sql.WHERE("packageId = #{packageId}");
        }
        if (status != null) {
            sql.WHERE("status = #{status}");
        }
        if (feeMode != null) {
            sql.WHERE("feeMode = #{feeMode}");
        }
        if (superpositionMode != null) {
            sql.WHERE("superpositionMode = #{superpositionMode}");
        }
        return sql.toString();
    }


    /**
     * 生成添加代理商加油包的sql
     *
     * @param agentAddPackage 代理商加油包的pojo类
     * @return sql
     */
    public String addAgentAddPackage(AgentAddPackage agentAddPackage) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_agent_addPackage");
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("agentName", "#{agentName}");
        sql.VALUES("feeMode", "#{feeMode}");
        sql.VALUES("packageId", "#{packageId}");
        sql.VALUES("showName", "#{showName}");
        sql.VALUES("packageSize", "#{packageSize}");
        sql.VALUES("operator", "#{operator}");
        sql.VALUES("rebateMode", "#{rebateMode}");
        sql.VALUES("totalStatus", "#{totalStatus}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("originalPrice", "#{originalPrice}");
        sql.VALUES("packagePrice", "#{packagePrice}");
        sql.VALUES("packageCost", "#{packageCost}");
        return sql.toString();
    }

    /**
     * 生成查找代理商资费计划sql
     *
     * @param operator    运营商
     * @param channelName 通道名称
     * @param showName    展示名称
     * @param agentName   代理名称
     * @param packageId   套餐包编码
     * @param status      状态
     * @param feeMode     计费模式
     * @return sql
     */
    public String findAgentAddPackage(Integer operator, String channelName, String showName, String agentName, String packageId, Integer status, Integer feeMode) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_agent_addPackage");

        if (StringUtils.isNotBlank(showName)) {
            sql.WHERE("showName = #{showName}");
        }
        if (operator != null) {
            sql.WHERE("operator = #{operator}");
        }
        if (StringUtils.isNotBlank(channelName)) {
            sql.WHERE("channelName = #{channelName}");
        }
        if (StringUtils.isNotBlank(agentName)) {
            sql.WHERE("agentName = #{agentName}");
        }
        if (status != null) {
            sql.WHERE("status = #{status}");
        }
        if (StringUtils.isNotBlank(packageId)) {
            sql.WHERE("packageId = #{packageId}");
        }

        if (feeMode != null) {
            sql.WHERE("feeMode = #{feeMode}");
        }
        return sql.toString();
    }

    /**
     * 生成添加物联网卡sql
     *
     * @param iotCard 物理网卡pojo类
     * @return sql
     */
    public String addIotCard(IotCard iotCard) {
        SQL sql = new SQL();
        sql.INSERT_INTO("iot_card");
        sql.VALUES("cardId", "#{cardId}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("networkStatus", "#{networkStatus}");
        sql.VALUES("useSize", "#{useSize}");
        sql.VALUES("totalSize", "#{totalSize}");
        sql.VALUES("agentName", "#{agentName}");
        sql.VALUES("channelName", "#{channelName}");
        sql.VALUES("cardType", "#{cardType}");
        sql.VALUES("cardDetail", "#{cardDetail}");
        sql.VALUES("settlementDate", "#{settlementDate}");
        sql.VALUES("addTime", "#{addTime}");
        sql.VALUES("renew", "#{renew}");
        sql.VALUES("renewDetail", "#{renewDetail}");
        sql.VALUES("trafficPool", "#{trafficPool}");
        sql.VALUES("operator", "#{operator}");
        return sql.toString();
    }

    /**
     * 根据条件查询
     *
     * @param startCardId   开始卡号
     * @param endCardId     结束卡号
     * @param cardId        卡号
     * @param operator      运营商
     * @param status        状态
     * @param channelName   通道名称
     * @param agentName     代理商名称
     * @param trafficPool   是否是流量池
     * @param networkStatus 网络状态
     * @return sql
     */
    public String findByChooseList(String startCardId, String endCardId, String cardId,
                                   Integer operator, Integer status, String channelName, String agentName,
                                   Integer trafficPool, Integer networkStatus) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("iot_card");
        if (StringUtils.isNotBlank(startCardId) && StringUtils.isNotBlank(endCardId)){
            sql.WHERE("cardId between #{startCardId} and #{endCardId}");
        }
        if (StringUtils.isNotBlank(cardId)){
            sql.WHERE("cardId = #{cardId}");
        }
        if (StringUtils.isNotBlank(channelName)){
            sql.WHERE("channelName = #{channelName}");
        }
        if (StringUtils.isNotBlank(agentName)){
            sql.WHERE("agentName = #{agentName}");
        }
        if (operator != null){
            sql.WHERE("operator = #{operator}");
        }
        if (status != null){
            sql.WHERE("status = #{status}");
        }
        if (trafficPool != null) {
            sql.WHERE("trafficPool = #{trafficPool}");
        }
        if (networkStatus != null){
            sql.WHERE("networkStatus = #{networkStatus}");
        }
        return sql.toString();
    }

}
