package org.learning.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.DTO.AuthDTORes;
import org.learning.springsecurity.DTO.AuthenticationDTOReq;
import org.learning.springsecurity.DTO.RegisterDTOReq;
import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.config.JwtService;
import org.learning.springsecurity.entity.User;
import org.learning.springsecurity.enums.RoleEnum;
import org.learning.springsecurity.exception.InvalidCredentialsException;
import org.learning.springsecurity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDTORes register(RegisterDTOReq request, RoleEnum roleEnum) {

        User newUser = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDate.now())
                .roleEnum(roleEnum)
                .build();
        newUser.setUpdatedAt(newUser.getCreatedAt());
        User savedUser = userRepository.save(newUser);

        return UserDTORes.builder()
                .name(savedUser.getName())
                .surname(savedUser.getSurname())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    @Override
    public AuthDTORes authenticate(AuthenticationDTOReq request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            throw new InvalidCredentialsException("Email o contraseña incorrectos");
        }

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return AuthDTORes.builder()
                .token(token)
                .build();
    }
}
