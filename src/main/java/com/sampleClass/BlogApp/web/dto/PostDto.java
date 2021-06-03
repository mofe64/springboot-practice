package com.sampleClass.BlogApp.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.cloudinary.*;

@Data
public class PostDto {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @NotEmpty(message = "content cannot be empty")
    private String title;
    @NotNull(message = "Content cannot be null")
    @NotBlank(message = "Content cannot be blank")
    @NotEmpty(message = "content cannot be empty")
    private String content;
    private MultipartFile imageFile;
}
