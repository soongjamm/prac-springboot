package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save (PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);

    }

    //map은 collection이나 stream에서 하나의 요소씩 뽑아서 괄호안의 조건을 만족하도록 만들고 리턴하는 함수
    //.map(PostsListResponseDto::new) = .map(posts -> new PostsListResponseDto(posts))
    //위에서 posts란 stream에 저장된 데이터 하나하나를 의미함.
    //postRepository에서 넘어온 결과를(Posts entity) stream에 담고 PostsListResponseDto로 변환한 뒤 List로 반환한다.
    //사용자에게 보여줘야 하는 필드만 뽑아서 보여주기 위해 새로운 DTO를 사용하는 것이다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc () {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

}
