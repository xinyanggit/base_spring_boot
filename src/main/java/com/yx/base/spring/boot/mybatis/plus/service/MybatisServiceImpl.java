package com.yx.base.spring.boot.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yx.base.spring.boot.mybatis.plus.dao.UserMapper;
import com.yx.base.spring.boot.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yx start
 * @create 2020/4/25,22:58
 */
@Service("mybatisService")
public class MybatisServiceImpl extends ServiceImpl<UserMapper, User> implements MybatisService {


    @Override
    public List mybatisTest() {
        return baseMapper.selectList(null);
    }
}
