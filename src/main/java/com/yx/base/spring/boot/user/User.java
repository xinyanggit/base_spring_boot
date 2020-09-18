package com.yx.base.spring.boot.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-18 15:56
 */
@Data
@TableName("user")
public class User {

    @TableId
    private Long id;

    private String name;

    private String account;

    private String password;

    private Integer version;
    private LocalDateTime updateTime;
    
    private LocalDateTime createTime;
}
