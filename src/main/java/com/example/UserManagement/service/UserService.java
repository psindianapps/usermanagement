package com.example.UserManagement.service;

import com.example.UserManagement.config.AppConfig;
import com.example.UserManagement.config.FileConfig;
import com.example.UserManagement.dto.request.UserRequest;
import com.example.UserManagement.dto.response.UserResponse;
import com.example.UserManagement.entity.UserEntity;
import com.example.UserManagement.projection.UserProjection;
import com.example.UserManagement.repository.UserRepo;
import com.example.UserManagement.specification.UserSpecification;
import com.example.UserManagement.util.FileUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired private UserRepo userRepo;
    @Autowired private FileUrlUtil fileUrlUtil;
    @Autowired private FileConfig fileConfig;
    @Autowired private AppConfig appConfig;

    public List<UserResponse> alluser(
            Map<String, String> filter
    ) {
        int page = Integer.parseInt(filter.getOrDefault("page", "1"))-1;
//        int size = appConfig.getPageSize();
        int size = Integer.parseInt(filter.getOrDefault("size","15"));
        Pageable pageable =
                PageRequest.of(page, size);

        Specification<UserEntity> specification =
                UserSpecification.userSpecification(filter);

        Page<UserEntity> projection =
                userRepo.findAll(specification,pageable);


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd MMM, yyyy");

        return projection.getContent().stream().map(user -> {

            UserResponse response =
                    new UserResponse();

            response.setName(user.getName());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());

            response.setCreatedAt(user.getCreatedAt());
            response.setUpdatedAt(user.getUpdatedAt());

            response.setDob(
                    user.getDob() != null
                            ? user.getDob().format(formatter)
                            : null
            );

            response.setIsActive(
                    user.getIsActive() == 1
                            ? "active"
                            : "inactive"
            );

            response.setProfileImage(
                    user.getProfileImage() != null
                            ? fileUrlUtil.getImageUrl(
                            user.getProfileImage()
                    )
                            : ""
            );

            return response;

        }).toList();
    }

    public void createUser(UserRequest userRequest) throws IOException {
        MultipartFile profileImage = userRequest.getProfileImage();
        String uploadDir = "uploads/";
        Map<String, String> response;
        try {
             response =  fileConfig.uploadImage(uploadDir,profileImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setDob(userRequest.getDob());
        userEntity.setGender(userRequest.getGender());
        userEntity.setProfileImage(response.get("filePath"));
        userRepo.save(userEntity);
    }
}
