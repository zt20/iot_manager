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

import com.github.pagehelper.PageInfo;
import com.weida.domain.JsonData;
import com.weida.pojo.Agent;
import com.weida.service.AgentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/8
 * Time:10:20
 */
@RestController
public class AgentController extends BaseController {

    @Autowired
    AgentService agentService;

    @PostMapping("/api/v1/agent_add")
    public JsonData addAgent(Agent agent, HttpServletRequest request) {
        if (agentService.findByAgentAccount(agent.getAgentAccount())) {
            System.out.println("该代理商账号已经存在");
            return JsonData.buildFail("该代理商账号已经存在", null);
        }
        agent.setAddTime(new Date());
        agentService.addAgents(agent);
        showLog(request, "添加了代理商", agent);
        logUtil.addSystemUserLog(request, "添加了代理商", "增加");
        return JsonData.buildSuccess();
    }

    @PostMapping("/api/v1/agent_update")
    public JsonData updateAgent(Agent agent, HttpServletRequest request) {

        if (!agentService.findByAgentAccount(agent.getAgentAccount())) {
            return JsonData.buildFail("该代理商已经不存在", null);
        }

        if (agent.getPassword().equals(agentService.findByAgentId(agent.getId()).getPassword())) {
            return JsonData.buildFail("密码相同 不用修改", null);
        }
        agentService.updateAgent(agent);
        showLog(request, "修改了代理商", agent);
        logUtil.addSystemUserLog(request, "修改了代理商", "修改");
        return JsonData.buildSuccess();
    }

    @DeleteMapping("/api/v1/agent_delete")
    public JsonData deleteAgent(int id, HttpServletRequest request) {
        agentService.deleteAgent(id);
        showLog(request, "添加了代理商", id);
        logUtil.addSystemUserLog(request, "删除了代理商" + id, "删除");
        return JsonData.buildSuccess();
    }

    @GetMapping("/api/v1/agent_list")
    public PageInfo selectAgent(int page, String id, String agentAccount, String name, String status) {
        int agentId = NumberUtils.toInt(id, 0);
        int agentStatus = NumberUtils.toInt(status, 0);
        return agentService.selectAgent(page, 15, agentId, agentAccount, name, agentStatus);
    }

    @GetMapping("/api/v1/agent_id")
    public JsonData selectAgentById(String id) {
        int agentId = NumberUtils.toInt(id);
        Agent agent = agentService.findByAgentId(agentId);
        if (null == agent)
            return JsonData.buildSuccess(null, null);
        agent.setPassword("******");
        return JsonData.buildSuccess("", agent);
    }

    @PostMapping("/api/v1/agent_updateStatus")
    public JsonData updateStatus(@RequestParam(value = "ids[]") Integer[] ids, String action, HttpServletRequest request) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (Integer id : ids) {
            idList.add(id);
        }
        if ("ok".equals(action)) {
            agentService.updateAgentStatus(AgentService.AGENT_OK, idList);
            showLog(request, "修改了代理商状态", ids);
            logUtil.addSystemUserLog(request, "修改了代理商状态", "修改");
            return JsonData.buildSuccess();
        }
        if ("stop".equals(action)) {
            agentService.updateAgentStatus(AgentService.AGENT_STOP, idList);
            showLog(request, "修改了代理商状态", ids);
            logUtil.addSystemUserLog(request, "修改了代理商状态", "修改");
            return JsonData.buildSuccess();
        }
        return JsonData.buildFail("操作有误", null);
    }

    @GetMapping("/api/v1/agentName_all")
    public JsonData findAllAgentName() {
        return JsonData.buildSuccess(agentService.findAllAgentName());
    }

    @PostMapping("/api/v1/addAgent_money")
    public JsonData addAgentMoney(String account, String balance, HttpServletRequest request) {

        if (StringUtils.isBlank(account))
            return JsonData.buildFail("请选择代理商", null);
        if (!NumberUtils.isParsable(balance))
            return JsonData.buildFail("金额有问题", null);
        if (agentService.addAgentMoney(account, NumberUtils.toDouble(balance))) {
            showLog(request, "代理商充值" + balance, account);
            logUtil.addSystemUserLog(request, "代理商充值" + balance, "修改");
            return JsonData.buildSuccess();
        }
        return JsonData.buildFail("充值失败！", null);
    }
}

