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

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by IntelliJ IDEA.
 * User:zhangtao
 * Date:2019/2/18
 * Time:19:12
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

      @Override
      public void configurePathMatch(PathMatchConfigurer configurer) {
            //开启路径后缀匹配
            configurer.setUseRegisteredSuffixPatternMatch(true);
      }

      @Bean
      public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
            ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
            servletServletRegistrationBean.addUrlMappings("*.do");//过滤.do的url 不配置默认所有的
            return servletServletRegistrationBean;
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/re_login.*")
                    .excludePathPatterns("*.htm").excludePathPatterns("*.css").excludePathPatterns("*.js");
      }

//      @Override
//      public void addViewControllers(ViewControllerRegistry registry) {
//            System.out.println("viewcontroller");
//            registry.addViewController("/re_login").setViewName("/Login.htm");
//      }
}
