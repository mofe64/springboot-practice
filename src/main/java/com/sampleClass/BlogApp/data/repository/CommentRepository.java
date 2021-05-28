package com.sampleClass.BlogApp.data.repository;

import com.sampleClass.BlogApp.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
