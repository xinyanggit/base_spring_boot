package com.yx.base.spring.boot.mybatis.plus;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author yx start
 * @create 2020/4/25,22:55
 */
@Data
@TableName("user")
public class UserVo {
     private Long id;
     private String  name ;
    private String  password ;
    private String  username ;
    private Date createTime ;
}
