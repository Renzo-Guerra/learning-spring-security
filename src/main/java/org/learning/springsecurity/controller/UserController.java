package org.learning.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.entity.User;
import org.learning.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTORes> findAuthenticatedUser(){
        return ResponseEntity.ok(userService.findAuthenticatedUser());
    }

    @GetMapping
    public ResponseEntity<List<UserDTORes>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/full")
    public ResponseEntity<List<User>> findAllUsersFull(){
        return ResponseEntity.ok(userService.findAllUsersFull());
    }
}
