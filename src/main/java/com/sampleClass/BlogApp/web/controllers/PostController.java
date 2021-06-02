package com.sampleClass.BlogApp.web.controllers;

import com.sampleClass.BlogApp.service.post.PostService;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute(name = "post") @Valid PostDto postDto) {
        log.info("PostDto Received --> {}", postDto);
        return "index";
    }
}
