package com.jojoldu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
         return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //posts-save 라는 mustache 파일을 리턴한다. 라우팅
    }
}
