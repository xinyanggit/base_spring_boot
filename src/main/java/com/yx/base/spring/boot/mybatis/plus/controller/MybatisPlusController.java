package com.yx.base.spring.boot.mybatis.plus.controller;

import com.yx.base.spring.boot.mybatis.plus.service.MybatisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yx start
 * @create 2020/4/25,22:53
 */
@RestController
@AllArgsConstructor
public class MybatisPlusController {

   private   MybatisService mybatisService ;
    @RequestMapping("/user/mybatis/test")
    public List mybatisTest(){
       return  mybatisService.mybatisTest();
    }
}
