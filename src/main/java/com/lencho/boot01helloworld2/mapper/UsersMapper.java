package com.lencho.boot01helloworld2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lencho.boot01helloworld2.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * @author Lencho
 * @create 2021-07-24 12:27
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {
}
