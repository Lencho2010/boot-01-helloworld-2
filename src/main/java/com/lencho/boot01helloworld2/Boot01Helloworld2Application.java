package com.lencho.boot01helloworld2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.lencho.boot01helloworld2.mapper")
public class Boot01Helloworld2Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Helloworld2Application.class, args);
    }

}
