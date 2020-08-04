package com.yx.base.spring.boot.swagger.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 15:30
 */
@Data
@ApiModel
public class Person {

    @ApiModelProperty(name = "name",value = "用户姓名",required = true,notes = "用户姓名测试")
    private String name;

    @ApiModelProperty(name = "age",value = "用户年龄",required = true,notes = "用户年龄测试")
    private Integer age;

}
