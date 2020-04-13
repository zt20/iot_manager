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

import com.weida.pojo.SystemUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:10:45
 * 系统管理员dao层
 */
public interface SystemUserDao{

      @Insert("insert into iot_system_user(account, password, " +
                "type, name, mobile, email,  " +
                "status, addTime) values (#{account}, #{password}, #{type}," +
                " #{name}, #{mobile}, #{email}," +
                "#{status},#{addTime})")
      void addSystemUser(SystemUser systemUser);

      @Delete("delete from iot_system_user where account = #{account}")
      void deleteSystemUser(String account);

//      @Update("update iot_system_user set password = #{password}, type = #{type}, name=#{name}, mobile = #{mobile}, email = #{email},status=#{status},addTime=#{addTime}")
      @UpdateProvider(type = ProviderSql.class, method = "updateSystemUserSql")
      void updateSystemUser(SystemUser systemUser);

      @Select("select * from iot_system_user where account = #{account}")
      SystemUser findByAccount(String account);

      @Select("select * from iot_system_user")
      List<SystemUser> findAll();

}
