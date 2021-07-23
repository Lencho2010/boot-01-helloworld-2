package com.lencho.boot01helloworld2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Lencho
 * @create 2021-07-23 21:04
 */
@ImportResource("classpath:bean.xml")
@Configuration(proxyBeanMethods = false)
public class MyConfig {


}
