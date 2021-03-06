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

import com.weida.pojo.SystemUserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:10:46
 * 管理员操作日志dao层
 */
public interface SystemUserLogDao{

      @SelectProvider(type = ProviderSql.class, method = "querySystemUserLogSql")
      List<SystemUserLog> findByAccount(String account, String startTime, String endTime, String optType, String Text1);

      @Insert("insert into iot_system_user_log(account, operationType, operationIp," +
                " operationTime, instruction) values (#{account}, #{operationType}, #{operationIp}," +
                " #{operationTime}, #{instruction})")
      void addSystemUserLog(SystemUserLog systemUserLog);


}

