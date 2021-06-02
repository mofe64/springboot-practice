package com.sampleClass.BlogApp.service.post;

import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.data.repository.PostRepository;
import com.sampleClass.BlogApp.exceptions.NullPostObjectException;
import com.sampleClass.BlogApp.web.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postServiceImpl = new PostServiceImpl();
    Post testPost;

    @BeforeEach
    void setUp() {
        testPost = new Post();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenTheSaveMethodIsCalled_thenRepositoryIsCalledOnceTest() throws NullPostObjectException {
        when(postServiceImpl.savePost(new PostDto())).thenReturn(testPost);
        PostDto postDto = new PostDto();
        postServiceImpl.savePost(new PostDto());

        verify(postRepository, times(1)).save(testPost);

    }
}