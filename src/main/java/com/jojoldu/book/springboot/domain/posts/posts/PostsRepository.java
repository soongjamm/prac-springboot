package com.jojoldu.book.springboot.domain.posts.posts;

import com.jojoldu.book.springboot.domain.posts.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
