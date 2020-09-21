package com.yx.base.spring.boot.jd.controller;

import com.yx.base.spring.boot.jd.service.JdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 京东数据获取demo
 *
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-21 11:28
 */
@RestController
@Api(value = "Jd ES 搜索使用", description = "Jd ES 搜索使用")
public class DataController {

    @Resource
    private JdService jdService;

    @GetMapping("/jd/parse")
    @ApiOperation(value = "初始化操作，初始化关键词")
    public Boolean initDataByKeyWord(@NotNull @RequestParam("kw") String keyWord) {
        return jdService.joinEsData(keyWord);
    }


    @GetMapping("/search/{keyWord}/{pageNo}/{pageSize}")
    @ApiOperation(value = "从Es中搜索查询")
    public List<Map<String, Object>> searchData(@PathVariable("keyWord") String keyWord,
                                                @PathVariable("pageNo") int pageNo,
                                                @PathVariable("pageSize") int pageSize) throws IOException {
        return jdService.searchPage(keyWord, pageNo, pageSize);
    }


    @GetMapping("/search/highlighter/{keyWord}/{pageNo}/{pageSize}")
    @ApiOperation(value = "从Es中搜索查询--高亮显示")
    public List<Map<String, Object>> searchHighlighterPage(@PathVariable("keyWord") String keyWord,
                                                           @PathVariable("pageNo") int pageNo,
                                                           @PathVariable("pageSize") int pageSize) throws IOException {
        return jdService.searchHighlighterPage(keyWord, pageNo, pageSize);
    }


}
