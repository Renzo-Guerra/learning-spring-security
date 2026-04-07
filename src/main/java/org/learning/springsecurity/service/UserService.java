package org.learning.springsecurity.service;

import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.entity.User;

import java.util.List;

public interface UserService {
    UserDTORes findAuthenticatedUser();
    List<UserDTORes> findAllUsers();
    List<User> findAllUsersFull();
}
