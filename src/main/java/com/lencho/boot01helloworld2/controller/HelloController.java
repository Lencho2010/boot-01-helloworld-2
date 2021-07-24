package com.lencho.boot01helloworld2.controller;

import com.lencho.boot01helloworld2.bean.Person;
import com.lencho.boot01helloworld2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lencho
 * @create 2021-07-23 21:01
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring boot2";
    }

    @Autowired
    private Person person;

    @RequestMapping("/person")
    public String person() {
        return person.toString();
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/update")
    //@Secured({"ROLE_sale", "ROLE_manager"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update() {
        return "hello update";
    }

    @RequestMapping("/getAll")
    @PostAuthorize("hasAuthority('admins')")
    @PostFilter("filterObject.password == 'lisi'")
    public List<Users> getAllUser(){
        ArrayList<Users> list = new ArrayList<>();

        list.add(new Users(1,"zhangsan","123"));
        list.add(new Users(2,"lisi","456"));
        return list;
    }
}
