package com.yx.base.spring.boot.mybatis.plus.ne2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 17:25
 */
@ApiModel("接收参数数据")
@Data
public class UserBasicRequest {

    @ApiModelProperty(name = "userAccount", value = "用户账号")
    private String userAccount;

    @ApiModelProperty(name = "userName", value = "用户姓名")
    private String userName;
    @ApiModelProperty(name = "userDetailSnapshot", value = "用户信息快照")
    private String userDetailSnapshot;

    @ApiModelProperty(name = "orderNumber", value = "工单编号")
    private Long orderNumber;

    @ApiModelProperty(name = "phoneNumber1", value = "手机号码1")
    private String phoneNumber1;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endTime;

}
