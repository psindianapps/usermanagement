package com.example.UserManagement.repository;

import com.example.UserManagement.entity.UserEntity;
import com.example.UserManagement.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Query(value = """
    Select 
        name as name,
        username as username,
        email as email,
        dob as dob,
        gender as gender,
        profile_image as profileImage,
        is_active as isActive,
        created_at as createdAt,
        updated_at as updatedAt
    From users
    """, nativeQuery = true)
    List<UserProjection> getAllUsers();
}
