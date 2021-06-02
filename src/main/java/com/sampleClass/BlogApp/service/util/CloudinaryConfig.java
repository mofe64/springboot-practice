package com.sampleClass.BlogApp.service.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CloudinaryConfig {
    @Value("${cloud_name}")
    private String cloudName;
    @Value("${api_key}")
    private String apiKey;
    @Value("${api_secret}")
    private String apiSecret;
}
