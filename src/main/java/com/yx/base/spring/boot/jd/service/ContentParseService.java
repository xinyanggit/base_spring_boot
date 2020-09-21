package com.yx.base.spring.boot.jd.service;

import com.yx.base.spring.boot.jd.mode.Good;
import com.yx.base.spring.boot.jd.utils.ParseHtmlUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-21 11:46
 */
@Service
public class ContentParseService {

    public List<Good> parse(String keyWord) throws Exception {
        return new ParseHtmlUtils().getGoodByKeyWord(keyWord);
    }
}
