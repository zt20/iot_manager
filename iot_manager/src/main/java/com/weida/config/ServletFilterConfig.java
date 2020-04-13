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
package com.weida.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:16:32
 */
@Configuration
public class ServletFilterConfig {
      /**
       * 因为这里是使用main方法加载的所以没有配置文件这里需要将struts2的核心拦截器实例
       * 否则无法访问struts2
       * 而web项目中struts2的配置文件如下：
       * <filter>
       * <filter-name>struts2</filter-name>
       * <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
       * </filter>
       * <filter-mapping>
       * <filter-name>struts2</filter-name>
       * <url-pattern>/*</url-pattern>
       * </filter-mapping>
       * 所以我们需要在这个配置类中将web中配置的拦截器类创建这样才实现了struts2的核心配置
       * 而路径如果不配置的话默认的就是拦截所有路径
       *
       * @return
       */
//      @Bean
//      public StrutsPrepareAndExecuteFilter strutsPrepareAndExecuteFilter() {
//            return new StrutsPrepareAndExecuteFilter();
//      }
}
