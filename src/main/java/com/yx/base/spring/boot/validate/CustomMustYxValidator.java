package com.yx.base.spring.boot.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yx start
 * @create 2020/4/19,16:51
 */
public class CustomMustYxValidator   implements ConstraintValidator<CustomMustContainYx,String> {

    @Override
    public void initialize(CustomMustContainYx constraintAnnotation) {
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        if(!(o instanceof  String) || o ==null || o.toString().trim().equals("")){
            return false;
        }
        if(o.toString().toUpperCase().contains("YX")){
            return true;
        }
        return false ;
    }
}
