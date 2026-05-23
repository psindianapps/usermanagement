package com.example.UserManagement.util;

import com.example.UserManagement.config.AppConfig;
import org.springframework.stereotype.Component;

@Component
public class FileUrlUtil {
    private final AppConfig appConfig;

    public FileUrlUtil(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public String getImageUrl(String fileName){
        if (fileName == null || fileName.isBlank()) {
            return null;
        }

        return appConfig.getBaseUrl() + fileName;
    }
}
