package com.sampleClass.BlogApp.service.post;

import com.sampleClass.BlogApp.data.models.Comment;
import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.data.repository.PostRepository;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(PostDto postDto) {
        Post post = new Post();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(postDto, post);
        log.info("Post model after mapping --> {}", post);
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return null;
    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public Post findPostById(Integer id) {
        return null;
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer integer, Comment comment) {
        return null;
    }
}
