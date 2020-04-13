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

import com.github.pagehelper.PageInfo;
import com.weida.IotApplication;
import com.weida.pojo.SystemUser;
import com.weida.service.SystemUserLogService;
import com.weida.service.SystemUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/20
 * Time:22:09
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotApplication.class)
public class SystemUserLogTest {

    @Autowired
    SystemUserLogService systemUserLogService;

    @Autowired
    SystemUserService systemUserService;

    @Test
    public void systemUserLogTest() {
        systemUserLogService.findByAccount(1, 5, "admin", null, null, null, null);
    }

    @Test
    public void listSystem() {
        PageInfo<SystemUser> systemUserPageInfo = systemUserService.findAll(1, 5);
        List<SystemUser> systemUsers = systemUserPageInfo.getList();
        systemUsers.forEach(System.out::println);
    }

    @Test
    public void md5Test() {
        System.out.println(DigestUtils.md5Hex("ushadjshdjkshdjkahsdjhaskjdhsajdhjsahdjsahdjashjdaskdjsdhaskjdjhjk"));
    }


    @Test
    public void TestBoolean() {
        String something = "hello";
        System.out.println(this.isNull(something));
    }

    public boolean isNull(String something) {
        return something != null;
    }

    @Test
    public void test11(){
        int[] aa = {2,7,11,15};
        aa = this.twoSum(aa,26);
        System.out.println(aa[0] + ";;" +aa[1]);
    }


    public int[] twoSum(int[] nums, int target) {
        int[] aa = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    aa[0] = i;
                    aa[1] = j;
                    return aa;
                }
            }
        }
        return aa;
    }

}
