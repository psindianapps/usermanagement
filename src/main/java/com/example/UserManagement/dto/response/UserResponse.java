package com.example.UserManagement.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String name;
    private String email;
    private String username;
    private LocalDate dob;
    private String gender;
    private int isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
