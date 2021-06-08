package com.sampleClass.BlogApp.service.cloud;

import com.cloudinary.utils.ObjectUtils;
import com.sampleClass.BlogApp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class CloudinaryCloudStorageServiceImplTest {

    @Autowired
    CloudStorageService cloudStorageService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void uploadImage(){
        File file = new File("D:\\Downloads\\BlogApp\\BlogApp\\src\\main\\resources\\static\\asset\\images\\amazon.png");
        Map<Object, Object> params = new HashMap<>();
        assertThat(file.exists()).isTrue();
        params.put("public_id", UUID.randomUUID().toString());
        params.put("folder", "blogappstuff");
        try{
           Map<?, ?> result = cloudStorageService.uploadImage(file, params);
           log.info("returned map --> {}", result);
        } catch (IOException e) {
            log.error("Error occured --> {}", e.getMessage());
        }

    }

    @Test
    void uploadMultipartImageFile(){
        File file = new File("D:\\Downloads\\BlogApp\\BlogApp\\src\\main\\resources\\static\\asset\\images\\amazon.png");
        Map<Object, Object> params = new HashMap<>();
        assertThat(file.exists()).isTrue();
        params.put("public_id", UUID.randomUUID().toString());
        params.put("folder", "blogappstuff");
        try{
            Map<?, ?> result = cloudStorageService.uploadImage(file, params);
            log.info("returned map --> {}", result);
        } catch (IOException e) {
            log.error("Error occured --> {}", e.getMessage());
        }
    }

    @Test
    void uploadMultipartImageFileTest() throws IOException {
        PostDto postDto = new PostDto();
        postDto.setTitle("Test");
        postDto.setContent("Test");

        Path path = Paths.get("D:\\Downloads\\BlogApp\\BlogApp\\src\\main\\resources\\static\\asset\\images\\blog-image1.jpg");
        MultipartFile multipartFile = new MockMultipartFile("images.jpeg", "images.jpeg",
                "img/jpeg", Files.readAllBytes(path));

        log.info("Multipart Object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();
        postDto.setImageFile(multipartFile);

        log.info("File name --> {}", postDto.getImageFile().getOriginalFilename());

        cloudStorageService.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "blogapp/"+ extractFileName(Objects.requireNonNull(postDto.getImageFile().getOriginalFilename()))
        ));
    }
    private String extractFileName(String filename){
        return filename.split("\\.")[0];
    }
}