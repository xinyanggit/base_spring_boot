package com.yx.base.spring.boot.user.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yx start
 * @create 2019/12/29,13:03
 */
@Mapper
public interface UserDao {

    public List getUserList();
}
