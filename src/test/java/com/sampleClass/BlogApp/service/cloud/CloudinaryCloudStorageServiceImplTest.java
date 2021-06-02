package com.sampleClass.BlogApp.service.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
}