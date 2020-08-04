package com.yx.base.spring.boot.mybatis.plus.ne2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.base.spring.boot.mybatis.plus.ne2.model.UserBasicInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 16:28
 */
@Mapper
public interface MybatisNewMapper extends BaseMapper<UserBasicInfo> {

}
