package com.yx.base.spring.boot.rest.controller;

import com.yx.base.spring.boot.rest.service.RestfulService;
import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultGenerator;
import com.yx.base.spring.boot.vo.UserCustomDTO;
import com.yx.base.spring.boot.vo.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**对外提供接口类
 * @author yx start
 * @create 2020/3/19,17:41
 */
@RestController
public class RestfulController {

    @Resource
    private RestfulService restfulService ;

    /**
     * 传入可变参数。进行测试异常统一处理方式
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public Result listData(@PathVariable(name="id") int id ){
        List list =  restfulService.listData(id) ;
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/user/save")
    public Result saveUser(@RequestBody @Validated UserDTO userDTO ){
        UserDTO userDTORes = restfulService.saveUser(userDTO);
        return ResultGenerator.genSuccessResult(userDTORes);
    }

    @PostMapping("/user/custom_save")
    public Result customSaveUser(@RequestBody @Validated UserCustomDTO userDTO ){
        return ResultGenerator.genSuccessResult(userDTO);
    }


}
