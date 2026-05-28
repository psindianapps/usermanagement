package com.example.UserManagement.repository;

import com.example.UserManagement.entity.UserEntity;
import com.example.UserManagement.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @Query(value = """
    SELECT 
        name AS name,
        username AS username,
        email AS email,
        dob AS dob,
        gender AS gender,
        profile_image AS profileImage,
        is_active AS isActive,
        created_at AS createdAt,
        updated_at AS updatedAt
    FROM users
    WHERE is_deleted = 'N'
    """,
            countQuery = """
    SELECT COUNT(*) 
    FROM users
    WHERE is_deleted = 'N'
    """,
            nativeQuery = true)
    Page<UserProjection> getAllUsers(Pageable pageable);

}
