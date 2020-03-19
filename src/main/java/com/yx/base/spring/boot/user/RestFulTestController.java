package com.yx.base.spring.boot.user;

import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultGenerator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yx start
 * @create 2020/3/19,17:41
 */
@RestController
public class RestFulTestController {

    @RequestMapping("/list/{id}")
    public Result listData(@PathVariable(name="id") int id ){
        List list = genData(id);
        return ResultGenerator.genSuccessResult(list);
    }

    private List genData(int num ){
        List<Map<String,Object>> list = new ArrayList<>(num);
        for (int j = 0; j < num; j++) {
         Map map = new HashMap(2);
         map.put("name"+j,j);
         map.put("age"+j,j);
            list.add(map);
        }
        return list ;
    }
}
