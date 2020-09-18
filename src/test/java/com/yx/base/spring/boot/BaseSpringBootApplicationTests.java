package com.yx.base.spring.boot;

import com.yx.base.spring.boot.es.search.JdShop;
import com.yx.base.spring.boot.es.search.SearchUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@SpringBootTest
class BaseSpringBootApplicationTests {

    @Test
    void contextLoads() {
    }

    // URL :  https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=32d26b70a2ca494dbaea728c7e3fbe59
    @Test
    void getDataFromJd() throws IOException {
         String url = "https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=32d26b70a2ca494dbaea728c7e3fbe59";
        Document doc = Jsoup.parse(new URL(url), 30000);
        Element rootEle = doc.getElementById("J_goodsList");
        Elements li = rootEle.getElementsByTag("li");
        System.out.println("==============");
        System.out.println(li.html());
        System.out.println("==============");
        for (Element element : li) {
         //   String img = element.getElementsByTag("img").eq(0).attr("src");
            Elements elementsByClass = element.getElementsByClass("p-img");
            String img = elementsByClass.eq(0).select("img").eq(0).attr("src");
            System.out.println("商品图片===>"+img);
            Elements pPrice = element.getElementsByClass("p-price");
            String text = pPrice.eq(0).text();
            System.out.println("商品价格===>"+text);
            Elements pName = element.getElementsByClass("p-name");
            String text1 = pName.eq(0).text();
            System.out.println("商品标题===>"+text1);
        }
    }

    public static void main(String[] args) throws IOException {
        List<JdShop> list = SearchUtils.getListByKey("java");
        System.out.println("===================>结果显示<=========================");
        list.forEach(System.out::println);
    }

}
