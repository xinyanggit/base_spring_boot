package com.yx.base.spring.boot.rest.service;

import com.yx.base.spring.boot.exception.CustomServiceException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yx start
 * @create 2020/3/22,14:00
 */
@Service
public class RestfulServiceImpl implements  RestfulService {
    @Override
    public List listData(int num) {
        // 在这里进行模拟代码出错
        if(num==0){
            throw  new CustomServiceException("id 传参出错误"+num) ;
        }
        return  genData(num) ;
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
