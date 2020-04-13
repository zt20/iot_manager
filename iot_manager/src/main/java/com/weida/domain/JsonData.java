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
package com.weida.domain;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/19
 * Time:13:43
 * 该类是用来将后台处理后的数据通过JSON格式传给前台
 */
public class JsonData {

      public JsonData() {
            super();
      }

      public JsonData(int code, String msg, Object object) {
            super();
            this.code = code;
            this.msg = msg;
            this.object = object;
      }

      private int code;

      private String msg;

      private Object object;

      public int getCode() {
            return code;
      }

      public void setCode(int code) {
            this.code = code;
      }

      public String getMsg() {
            return msg;
      }

      public void setMsg(String msg) {
            this.msg = msg;
      }

      public Object getObject() {
            return object;
      }

      public void setObject(Object object) {
            this.object = object;
      }

      public static JsonData buildSuccess() {
            return new JsonData(0, null, null);
      }

      public static JsonData buildSuccess(Object data) {
            return new JsonData(0, null, data);
      }

      public static JsonData buildSuccess(String msg, Object data) {
            return new JsonData(0, msg, data);
      }

      public static JsonData buildFail() {
            return new JsonData(-1, null, null);
      }

      public static JsonData buildFail(Object data) {
            return new JsonData(-1, null, data);
      }

      public static JsonData buildFail(String msg, Object data) {
            return new JsonData(-1, msg, data);
      }
}
