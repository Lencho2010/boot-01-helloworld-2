package com.lencho.boot01helloworld2.controller;

import com.lencho.boot01helloworld2.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lencho
 * @create 2021-07-23 21:01
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring boot2";
    }

    @Autowired
    private Person person;

    @RequestMapping("/person")
    public String person(){
        return person.toString();
    }

    @GetMapping("/index")
    public String index(){
        return "hello index";
    }
}
