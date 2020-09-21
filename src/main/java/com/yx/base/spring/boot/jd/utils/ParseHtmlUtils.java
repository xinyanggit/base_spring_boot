package com.yx.base.spring.boot.jd.utils;

import com.yx.base.spring.boot.jd.mode.Good;
import com.yx.base.spring.boot.jd.service.JdGoodsConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-21 11:33
 */
public class ParseHtmlUtils {

    /**
     * 通过关键词获取对应的商品信息
     *
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Good> getGoodByKeyWord(String keyWord) throws Exception {
        List<Good> list = new ArrayList<>();
        String url = JdGoodsConstant.JD_URL + keyWord;
        Document doc = Jsoup.parse(new URL(url), 30000);
        Element rootEle = doc.getElementById("J_goodsList");
        Elements li = rootEle.getElementsByTag("li");
        for (Element element : li) {
            Elements elementsByClass = element.getElementsByClass("p-img");
            String imgUrl = elementsByClass.eq(0).select("img").eq(0).attr("src");
            Elements pPrice = element.getElementsByClass("p-price");
            String price = pPrice.eq(0).text();
            Elements pName = element.getElementsByClass("p-name");
            String name = pName.eq(0).text();
            list.add(Good.builder().imgUrl(imgUrl).price(price).title(name).build());
        }
        return list;
    }

}
