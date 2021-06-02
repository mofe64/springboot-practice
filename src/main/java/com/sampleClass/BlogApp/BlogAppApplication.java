package com.sampleClass.BlogApp;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.sampleClass.BlogApp.service.util.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	@Autowired
	CloudinaryConfig cloudinaryConfig;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	@Bean
	public Cloudinary getCloudinary(){
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudinaryConfig.getCloudName(),
				"api_key", cloudinaryConfig.getApiKey(),
				"api_secret", cloudinaryConfig.getApiSecret()));
		return cloudinary;

	}
}
