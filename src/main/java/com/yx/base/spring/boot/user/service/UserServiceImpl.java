package com.yx.base.spring.boot.user.service;

import com.yx.base.spring.boot.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yx start
 * @create 2019/12/29,13:03
 */
@Service("userService")
public class UserServiceImpl implements   UserService {

    @Autowired
    private UserDao userDao ;

    @Override
    public List getUserList() {
        return userDao.getUserList();
    }
}
