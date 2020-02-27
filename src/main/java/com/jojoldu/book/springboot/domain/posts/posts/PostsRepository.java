package com.jojoldu.book.springboot.domain.posts.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    //@Query 어노테이션 안의 SQL문을 그 아래에 선언된 변수에 함수화 시킨다.
    //FROM Posts p --> p는 Posts의 alias 이다.
    //DESC = Descending. 내림차순
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
