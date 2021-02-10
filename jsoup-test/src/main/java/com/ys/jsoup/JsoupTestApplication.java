package com.ys.jsoup;

import com.ys.jsoup.utils.JsoupUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class JsoupTestApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JsoupTestApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        JsoupUtil.getContent(s);
    }
}
