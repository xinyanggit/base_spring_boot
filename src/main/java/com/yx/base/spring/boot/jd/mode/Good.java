package com.yx.base.spring.boot.jd.mode;

import lombok.Builder;
import lombok.Data;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-21 11:38
 */
@Data
@Builder
public class Good {
    private String imgUrl;
    private String price;
    private String title;
}
