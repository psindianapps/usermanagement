package com.example.UserManagement.repository;

import com.example.UserManagement.dto.response.UserResponse;
import com.example.UserManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Query(value = "Select name, username, email, dob, gender, is_active, created_at, updated_at From users")
    List<UserResponse> getAllUsers();
}
