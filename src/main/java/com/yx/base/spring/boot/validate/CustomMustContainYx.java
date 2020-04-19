package com.yx.base.spring.boot.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yx start
 * @create 2020/4/19,16:49
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomMustYxValidator.class)
public @interface CustomMustContainYx {
    String value() default  "必须包含YX字样";
    String message() default "必须包含YX字样";
    Class<?>[] groups() default {}; // 模拟其他，分场景使用
    Class<? extends Payload>[] payload() default {}; // 必须要，不然就报错
}
