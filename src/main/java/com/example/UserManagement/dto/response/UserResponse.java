package com.example.UserManagement.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String name;
    private String email;
    private String username;
    private String dob;
    private String profileImage;
    private String gender;
    private String isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
