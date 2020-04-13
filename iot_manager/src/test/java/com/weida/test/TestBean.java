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
package com.weida.test;

import com.weida.pojo.AgentTariff;
import com.weida.pojo.ChannelTariff;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/3/22
 * Time:9:32
 */

public class TestBean {

    @Test
    public void TestBean(){
        ChannelTariff channelTariff = new ChannelTariff();
        channelTariff.setChannelName("channelName");
        channelTariff.setRemark("remark");
        channelTariff.setTrafficType(1);
        AgentTariff agentTariff = new AgentTariff();
        System.out.println("--" + agentTariff.getChannelName());
        BeanUtils.copyProperties(channelTariff, agentTariff);
        System.out.println(agentTariff.getChannelName() + "=" + agentTariff.getAgentName()) ;
    }

    @Test
    public void TestToDouble(){
       double i =  NumberUtils.toDouble("5", 1.00D);
        System.out.println(i);

    }

}
