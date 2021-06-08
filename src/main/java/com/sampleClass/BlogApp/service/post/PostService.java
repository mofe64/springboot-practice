package com.sampleClass.BlogApp.service.post;

import com.sampleClass.BlogApp.data.models.Comment;
import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.exceptions.NullPostObjectException;
import com.sampleClass.BlogApp.exceptions.PostNotFoundException;
import com.sampleClass.BlogApp.web.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post savePost(PostDto postDto) throws NullPostObjectException;
    List<Post> findAllPosts();
    List<Post> findAllPostsInDescendingOrder();
    Post updatePost(PostDto postDto);
    Post findPostById(Integer id) throws PostNotFoundException;
    void deletePostById(Integer id);
    Post addCommentToPost(Integer integer, Comment comment);

}
