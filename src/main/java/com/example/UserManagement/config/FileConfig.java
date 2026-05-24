package com.example.UserManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Autowired private AppConfig appConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

    public Map<String, String> uploadImage(String uploadDir, MultipartFile image) throws IOException {
        Map<String,String> response = new HashMap<>();

        if(image == null){
            throw new RuntimeException("File not Found");
        }

        if(image.getSize() > appConfig.getMaxFileSize()){
            throw new RuntimeException("File size must not be exceed to 100KB");
        }

        File directory = new File(uploadDir);
        if(!directory.exists()){
            directory.mkdirs();
        }
        String fileName = System.currentTimeMillis()+"_"+image.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);
        String filePath = uploadDir+fileName;
        Files.copy(image.getInputStream(), path);
        response.put("filePath",filePath);
        response.put("fileName",fileName);
        return response;
    }
}