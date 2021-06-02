package com.sampleClass.BlogApp.service.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sampleClass.BlogApp.data.models.Comment;
import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.data.repository.PostRepository;
import com.sampleClass.BlogApp.exceptions.NullPostObjectException;
import com.sampleClass.BlogApp.service.cloud.CloudStorageService;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    CloudStorageService cloudStorageService;

    @Override
    public Post savePost(PostDto postDto) throws NullPostObjectException {
        if (postDto == null) {
            throw new NullPostObjectException("Post cannot be null");
        }
        Post post = new Post();

        if (postDto.getImageFile() != null) {
            Map<Object, Object> params = new HashMap<>();
            params.put("public_id", "blogapp/" + postDto.getImageFile().getName());
            params.put("overwrite", true);
            log.info("cloudinary params --> {}", params);
            try {
                cloudStorageService.uploadImage(postDto.getImageFile(), ObjectUtils.asMap(
                        "public_id", "blogapp/" + postDto.getImageFile().getName(),
                        "overwrite", true
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
