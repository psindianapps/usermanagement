package com.example.UserManagement.repository;

import com.example.UserManagement.entity.UserEntity;
import com.example.UserManagement.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    WHERE
        (
            name LIKE CONCAT('%', :search, '%')
            OR email LIKE CONCAT('%', :search, '%')
        )

    """,
    countQuery = """
            SELECT count(*) FROM users
            WHERE
                (
                    name LIKE CONCAT('%', :search, '%')
                    OR email LIKE CONCAT('%', :search, '%')
                )
        
            """,
    nativeQuery = true)
    Page<UserProjection> getAllUsers(
            @Param("search") String search,
            Pageable pageable
    );

}
