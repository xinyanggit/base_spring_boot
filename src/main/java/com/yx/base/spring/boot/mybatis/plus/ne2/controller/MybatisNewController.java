package com.yx.base.spring.boot.mybatis.plus.ne2.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yx.base.spring.boot.mybatis.plus.ne2.dto.UserBasicRequest;
import com.yx.base.spring.boot.mybatis.plus.ne2.service.MybatisNewService;
import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultGenerator;
import com.yx.base.spring.boot.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author yx start
 * @create 2020/4/25,22:53
 */
@Slf4j
@RestController("/mybatis")
@AllArgsConstructor
public class MybatisNewController {

    @Resource
    private MybatisNewService mybatisNewService;

    /**
     * 1、正常查询
     * 2、模糊查询
     * 3、多条件
     * 4、范围查询
     * 5、分页
     * 5、like notLike  LikeLeft LikeRight sort in ....
     */

    /**
     * 范围查询
     */
    @PostMapping("/getOne")
    @Order(1)
    @ApiOperation("正常查询")
    public Result<?> getOne() {
        int count = mybatisNewService.count();
        log.info(String.valueOf(count));
        return null;
    }

    /**
     * 范围查询
     */
    @PostMapping("/likeSelect")
    @Order(2)
    @ApiOperation("模糊查询")
    public Result<?> likeSelect() {
        int count = mybatisNewService.count();
        log.info(String.valueOf(count));
        return null;
    }


    @PostMapping("/mulConditionSelect")
    @Order(3)
    @ApiOperation("多条件查询")
    public Result<?> mulConditionSelect(@RequestBody UserBasicRequest userBasicRequest) {
        int count = mybatisNewService.count();
        log.info(String.valueOf(count));
        return null;
    }

    @PostMapping("/rangeSelect")
    @Order(4)
    @ApiOperation("范围查询")
    public Result<List<User>> rangeSelect(@RequestBody UserBasicRequest userBasicRequest) {
        List<User> list = mybatisNewService.list(Wrappers.<User>query().lambda().gt(Optional.ofNullable(userBasicRequest.getStartTime()).isPresent(), User::getCreateTime, userBasicRequest.getStartTime())
                .lt(User::getCreateTime, userBasicRequest.getEndTime()).like(User::getName, userBasicRequest.getUserName()));
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/page")
    @Order(5)
    @ApiOperation("分页查询")
    public Result<?> page() {
        int count = mybatisNewService.count();
        log.info(String.valueOf(count));
        return null;
    }

}
