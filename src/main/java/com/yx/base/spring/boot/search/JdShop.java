package com.yx.base.spring.boot.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-11 9:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JdShop {
    private String title ;
    private String price ;
    private String img ;

}
