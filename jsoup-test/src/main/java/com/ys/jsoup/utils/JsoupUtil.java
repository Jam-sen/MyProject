package com.ys.jsoup.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {
    public static List<String> getContent(String goodsName) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + goodsName;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element j_goodsList = document.getElementById("J_goodsList");
        Elements liList = j_goodsList.getElementsByTag("li");
        List<String> list = new ArrayList<>();
        for (Element li : liList) {
            Elements name = li.getElementsByClass("p-name");
            Elements a = li.getElementsByTag("a");
            String href = a.attr("href");
//            String img = li.getElementsByTag("img").attr("source-data-lazy-advertisement");
            String name1 = name.text();
            if (name1!=null&&!"".equals(name1)) {
                list.add(name1+"--->>> https:"+href);
            }
        }
        return list;
    }
}
