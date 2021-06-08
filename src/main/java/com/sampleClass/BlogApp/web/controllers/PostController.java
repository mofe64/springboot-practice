package com.sampleClass.BlogApp.web.controllers;

import com.sampleClass.BlogApp.data.models.Post;
import com.sampleClass.BlogApp.exceptions.NullPostObjectException;
import com.sampleClass.BlogApp.exceptions.PostNotFoundException;
import com.sampleClass.BlogApp.service.post.PostService;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getIndex(Model model) {
        List<Post> postList = postService.findAllPostsInDescendingOrder();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "create";
    }
/*
Valid annotation marks the particular field for validation
the result of the validation is stored in the binding result object
 */
    @PostMapping("/save")
    public String savePost(@ModelAttribute(name = "post") @Valid PostDto postDto,
                           BindingResult result, Model model) {
        log.info("PostDto Received --> {}", postDto);
        if(result.hasErrors()){
            return "create";
        }

        try {
            postService.savePost(postDto);
        } catch (NullPostObjectException e) {
            log.info("Exception occurred --> {}", e.getMessage());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error",true);
            model.addAttribute("errorMessage", "Title Not");
            return "create";
        }
        return "redirect:/posts";
    }

    /*
    * creates and add a global model object to all templates
    *
    */
    @ModelAttribute
    public void createPostModel(Model model){
        model.addAttribute("postDto", new PostDto());
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Optional<Integer> id, Model model) {
        try {
          Post post = postService.findPostById(id.orElse(-1));
          model.addAttribute(post);
          return "post";
        } catch (PostNotFoundException e) {
            return "redirect:/posts";
        }
    }
}
