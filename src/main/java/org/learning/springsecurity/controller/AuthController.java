package org.learning.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.DTO.AuthDTORes;
import org.learning.springsecurity.DTO.AuthenticationDTOReq;
import org.learning.springsecurity.DTO.RegisterDTOReq;
import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.enums.RoleEnum;
import org.learning.springsecurity.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTORes> register(@RequestBody RegisterDTOReq request){
        return ResponseEntity.ok(authService.register(request, RoleEnum.USER));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserDTORes> adminRegister(@RequestBody RegisterDTOReq request){
        return ResponseEntity.ok(authService.register(request, RoleEnum.ADMIN));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTORes> authenticate(@RequestBody AuthenticationDTOReq request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
