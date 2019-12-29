package com.yx.base.spring.boot.user.controller;

import com.yx.base.spring.boot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yx start
 * @create 2019/12/29,13:02
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService ;

    @RequestMapping("/user/list")
    public List getUserList(){
      return   userService.getUserList();
    }

}
