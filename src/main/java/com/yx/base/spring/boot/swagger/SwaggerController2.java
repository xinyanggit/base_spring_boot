package com.yx.base.spring.boot.swagger;

import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultGenerator;
import com.yx.base.spring.boot.swagger.vo.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-04 15:19
 */
@RestController
@Api("swagger2 测试使用")
public class SwaggerController2 {

    public List<String> randomGenStr(int num) {
        List res = new ArrayList(num);
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int i1 = random.nextInt(100);
            res.add("test" + i1);
        }
        return res;

    }

    @GetMapping("/getNum2")
    @ApiOperation(value = "获取随机数2")
    @ApiImplicitParam(name = "num", value = "获取随机数", required = true, paramType = "query", defaultValue = "10" )
    public Result<List<String>> findPerson(@RequestParam("num") Integer num) {
        return ResultGenerator.genSuccessResult(randomGenStr(num));
    }

    @PostMapping("/savePerson2")
    @ApiOperation(value = "保存用户信息2")
    public Result<?> savePerson(@RequestBody Person person) {
        System.out.println(person);
        return ResultGenerator.genSuccessResult();
    }

}
