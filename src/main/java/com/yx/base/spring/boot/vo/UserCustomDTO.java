package com.yx.base.spring.boot.vo;

import com.yx.base.spring.boot.validate.CustomMustContainYx;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yx start
 * @create 2020/4/19,16:59
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomDTO {
    @CustomMustContainYx("必须包含yx")
    private String name ;
}
