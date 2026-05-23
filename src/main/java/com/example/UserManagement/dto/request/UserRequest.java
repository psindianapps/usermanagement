package com.example.UserManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email Format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "DOB is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Profile image is required")
    private MultipartFile profileImage;

    @NotBlank(message = "Gender is required")
    private String gender;
}
