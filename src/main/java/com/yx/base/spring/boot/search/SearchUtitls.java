package com.yx.base.spring.boot.search;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-08-11 9:41
 */
public class SearchUtitls {

    private SearchUtitls(){};

    public  static List<JdShop> getListByKey(String key) throws IOException {
        List<JdShop> list = new ArrayList<>();
        String url = SearchConst.preUrl + key + SearchConst.sufUrl;
        Document doc = Jsoup.parse(new URL(url), 30000);
        Element rootEle = doc.getElementById("J_goodsList");
        Elements li = rootEle.getElementsByTag("li");
        for (Element element : li) {
            String img = element.getElementsByTag("img").eq(0).attr("src");
            if(StringUtils.isBlank(img)){
                img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            }
            if(StringUtils.isBlank(img)){
                continue;
            }
             System.out.println("商品图片===>" + img);
            Elements pPrice = element.getElementsByClass("p-price");
            String price = pPrice.eq(0).text();
            System.out.println("商品价格===>" + price);
            Elements pName = element.getElementsByClass("p-name");
            String title = pName.eq(0).text();
            System.out.println("商品标题===>" + title);
            if(StringUtils.isBlank(title)&&StringUtils.isBlank(price)){
                continue;
            }
            list.add(JdShop.builder().title(title).img(img).price(price).build());
        }
        return list;
    }


}
