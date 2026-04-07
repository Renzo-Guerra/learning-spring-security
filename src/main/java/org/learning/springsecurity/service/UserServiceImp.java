package org.learning.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.entity.User;
import org.learning.springsecurity.exception.NotFoundException;
import org.learning.springsecurity.mapper.UserMapper;
import org.learning.springsecurity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        if(currentUser == null) throw new NotFoundException("Unable to retrieve current user information");

        return currentUser;
    }

    @Override
    public UserDTORes findAuthenticatedUser() {
        User currentUser = this.getCurrentUser();

        return UserMapper.userToUserDTORes(currentUser);
    }

    @Override
    public List<UserDTORes> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::userToUserDTORes)
                .toList();
    }

    @Override
    public List<User> findAllUsersFull() {
        return userRepository.findAll();
    }

}
