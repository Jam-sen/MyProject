package com.ys.jsoup;

import com.ys.jsoup.utils.JsoupUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JsoupTestApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JsoupTestApplication.class, args);
        JsoupUtil.getContent("java");
    }


}
