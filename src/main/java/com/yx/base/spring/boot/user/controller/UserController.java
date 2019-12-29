package com.yx.base.spring.boot.user.controller;

import com.yx.base.spring.boot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yx start
 * @create 2019/12/29,13:02
 */
//@RestController
@Controller
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/user/list")
    public ModelAndView getUserList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView( );
        modelAndView.addObject("userlist",userService.getUserList());
        modelAndView.setViewName("userlist");
        return   modelAndView;
    }

}
