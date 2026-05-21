package com.example.UserManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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

    @NotBlank(message = "DOB is required")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    private String gender;
}
