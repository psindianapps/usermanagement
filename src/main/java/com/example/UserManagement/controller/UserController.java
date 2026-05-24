package com.example.UserManagement.controller;


import com.example.UserManagement.dto.request.UserRequest;
import com.example.UserManagement.dto.response.UserResponse;
import com.example.UserManagement.projection.UserProjection;
import com.example.UserManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Component
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> alluser(@RequestParam Map<String,String> request){

        Map<String,Object> response = new HashMap<>();
        try{
            Page<UserResponse> userResponses = userService.alluser(request);
            response.put("status",200);
            response.put("data",userResponses);
        } catch (Exception e) {
            response.put("status",HttpStatus.BAD_REQUEST);
            response.put("message",e.getMessage());
        }
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> createUser(@ModelAttribute @Valid UserRequest request) {
        Map<String, Object> response = new HashMap<>();
        try{
            userService.createUser(request);
            response.put("status", 200);
            response.put("message", "User created successfully");
        } catch (Exception e){
            response.put("status", 400);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
