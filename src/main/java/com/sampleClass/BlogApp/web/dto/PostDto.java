package com.sampleClass.BlogApp.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDto {
    private String title;
    private String content;
    private MultipartFile imageFile;
}
