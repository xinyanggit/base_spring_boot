package com.yx.base.spring.boot.rest.service;

import com.yx.base.spring.boot.vo.UserDTO;

import java.util.List;

/**
 * @author yx start
 * @create 2020/3/22,14:00
 */
public interface RestfulService {

    public List listData(int id);

    UserDTO saveUser(UserDTO userDTO);
}
