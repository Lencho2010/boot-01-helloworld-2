package com.lencho.boot01helloworld2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lencho.boot01helloworld2.entity.Users;
import com.lencho.boot01helloworld2.mapper.UsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lencho
 * @create 2021-07-24 11:13
 */
@Slf4j
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用usersMapper方法查询数据库
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Users queryUser = usersMapper.selectOne(wrapper);
        if(queryUser == null){
            throw  new UsernameNotFoundException("用户名不存在");
        }

        System.out.println(queryUser);
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins");
        return new User(username, new BCryptPasswordEncoder().encode(queryUser.getPassword()), auths);
    }
}
