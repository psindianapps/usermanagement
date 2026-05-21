package com.example.UserManagement.projection;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserProjection {

    String getName();
    String getEmail();
    String getUsername();
    LocalDate getDob();
    String getGender();
    Integer getIsActive();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
