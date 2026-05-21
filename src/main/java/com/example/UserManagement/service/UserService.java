package com.example.UserManagement.service;

import com.example.UserManagement.dto.request.UserRequest;
import com.example.UserManagement.entity.UserEntity;
import com.example.UserManagement.projection.UserProjection;
import com.example.UserManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired private UserRepo userRepo;

    public List<UserProjection> alluser(Map<String, String> filter){
        return  userRepo.getAllUsers();
    }

    public void createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setDob(userRequest.getDob());
        userEntity.setGender(userRequest.getGender());
        userRepo.save(userEntity);
    }
}
