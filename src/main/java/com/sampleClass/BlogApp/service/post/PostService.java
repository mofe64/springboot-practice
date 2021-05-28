package com.sampleClass.BlogApp.service.post;

import com.sampleClass.BlogApp.data.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post savePost();
    List<Post> findAllPosts();
    
}
