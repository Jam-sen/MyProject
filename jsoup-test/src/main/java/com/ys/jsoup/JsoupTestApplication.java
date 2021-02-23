package com.ys.jsoup;

import com.ys.jsoup.utils.JsoupUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JsoupTestApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JsoupTestApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.next();
            List<String> content = JsoupUtil.getContent(s);
            content.forEach(System.out::println);
        }
    }
}
