package com.sampleClass.BlogApp.web.controllers;

import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.exceptions.NullPostObjectException;
import com.sampleClass.BlogApp.service.post.PostService;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getIndex(Model model) {
        List<Post> postList = postService.findAllPosts();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute(name = "post") @Valid PostDto postDto, Model model) {
        log.info("PostDto Received --> {}", postDto);
        try {
            postService.savePost(postDto);
        } catch (NullPostObjectException e) {
            log.info("Exception occurred --> {}", e.getMessage());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error",true);
            model.addAttribute("errorMessage", e.getMessage());
            return "create";
        }
        return "redirect:/posts";
    }

    /*
    * creates and add a global model object to all templates
    */
    @ModelAttribute
    public void createPostModel(Model model){
        model.addAttribute("postDto", new PostDto());
    }
}
