package com.sampleClass.BlogApp.data.repository;

import com.sampleClass.BlogApp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
