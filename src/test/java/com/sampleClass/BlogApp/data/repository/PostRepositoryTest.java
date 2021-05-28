package com.sampleClass.BlogApp.data.repository;

import com.sampleClass.BlogApp.data.models.Author;
import com.sampleClass.BlogApp.data.models.Comment;
import com.sampleClass.BlogApp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;


    @BeforeEach
    void setup(){
    }

    @Test
    void savePostToDBTest(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Some content");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();
    }

    @Test
    void savePostToDBTestFailsWithPostOfSameTitle(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Some content");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();


        Post blogPost2 = new Post();
        blogPost2.setTitle("What is fintech");
        blogPost2.setContent("Some content");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThrows(DataIntegrityViolationException.class , ()->postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaved_thenSaveAuthor(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Some content");
        log.info("Created a blog post --> {}", blogPost);

        Author author = new Author();
        author.setFirstname("John");
        author.setLastname("Wick");
        author.setEmail("john@email.com");
        author.setPhoneNumber("1234");

        //to achieve cascading we need to have a bi-directional relationship and then map the entities as seen below
        blogPost.setAuthor(author);
        author.addPost(blogPost);

        postRepository.save(blogPost);
        log.info("Blog post after saving --> {}", blogPost);

    }

    @Test
    void findAllPostsInTheDBTest(){
        List<Post> existingPosts = postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertThat(existingPosts.size()).isEqualTo(5);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void deletePostTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Retrieved post from db --> {}", savedPost );

        postRepository.deleteById(savedPost.getId());

        Post deletedPost = postRepository.findById(41).orElse(null);
        assertThat(deletedPost).isNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatePostTest(){
        Post postToUpdate = postRepository.findById(41).orElse(null);
        assertThat(postToUpdate).isNotNull();
        log.info("Retrieved post from db --> {}", postToUpdate);
        assertThat(postToUpdate.getTitle()).isEqualTo("post1");
        assertThat(postToUpdate.getContent()).isEqualTo("content1");
        postToUpdate.setTitle("Updated Title");
        postToUpdate.setContent("Updated Content");

        postRepository.save(postToUpdate);

        Post updatedPost = postRepository.findById(41).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedPost.getContent()).isEqualTo("Updated Content");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void addCommentToPost(){
        Post post  = postRepository.findById(41).orElse(null);
        assertThat(post).isNotNull();
        assertThat(post.getComments()).hasSize(0);
        log.info("retrieved post object from db --> {}", post);

        Comment comment = new Comment();
        comment.setCommentatorName("test");
        comment.setContent("content");
        comment.setDateCreated(LocalDate.now());

        post.addComment(comment);

        Post updatedPost  = postRepository.save(post);

        assertThat(updatedPost.getComments()).isNotNull();
        assertThat(updatedPost.getComments()).hasSize(1);
        assertThat(updatedPost.getComments().get(0).getCommentatorName()).isEqualTo("test");
        assertThat(updatedPost.getComments().get(0).getContent()).isEqualTo("content");

    }



}