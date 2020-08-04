package com.yx.base.spring.boot.mybatis.plus.ne2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yx.base.spring.boot.mybatis.plus.ne2.dao.MybatisNewMapper;
import com.yx.base.spring.boot.mybatis.plus.ne2.model.UserBasicInfo;
import org.springframework.stereotype.Service;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 16:28
 */
@Service("mybatisNewService")
public class MybatisNewServiceImpl  extends ServiceImpl<MybatisNewMapper,UserBasicInfo> implements MybatisNewService {


}
