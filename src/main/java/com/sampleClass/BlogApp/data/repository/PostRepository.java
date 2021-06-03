package com.sampleClass.BlogApp.data.repository;

import com.sampleClass.BlogApp.data.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findByTitle(String title);

    List<Post> findByOrderByDateCreatedDesc();

    @Query("select  p from Post p where p.title = :title")
    Post findByPostTitle(@Param("title") String title);


    @Query("select  p from Post p where p.title = ?1")
    Post findByPostTitle2(String title);

}
