package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@RequiredArgsConstructor는 final 혹은 @Nonnull 필드에 대해서 생성자를 생성한다.
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;


    //Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
    //컨트롤러에서 뷰로 객체를 전달해주는 것이다.
    //addAttribute속 첫번째 인자 "posts"는 단지 뷰에서 사용할 변수 이름일 뿐이고, potato가 되어도 무관하다. 뷰에서 사용할 변수 이름이다.
    @GetMapping("/")
    public String index(Model model) {
         model.addAttribute("posts", postsService.findAllDesc() );
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //posts-save 라는 mustache 파일을 리턴한다. 라우팅
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
