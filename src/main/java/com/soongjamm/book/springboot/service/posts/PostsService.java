package com.soongjamm.book.springboot.service.posts;

import com.soongjamm.book.springboot.domain.posts.Posts;
import com.soongjamm.book.springboot.domain.posts.PostsRepository;
import com.soongjamm.book.springboot.web.dto.PostsResponseDto;
import com.soongjamm.book.springboot.web.dto.PostsSaveRequestDto;
import com.soongjamm.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        return new PostsResponseDto(entity);
    }
}
