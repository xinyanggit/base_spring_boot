package com.yx.base.spring.boot.mybatis.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.base.spring.boot.mybatis.plus.UserVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yx start
 * @create 2020/4/25,22:57
 */
@Mapper
public interface UserMapper extends BaseMapper<UserVo> {
}
