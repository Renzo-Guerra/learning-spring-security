package org.learning.springsecurity.service;

import org.learning.springsecurity.DTO.UserDTORes;

import java.util.List;

public interface UserService {
    UserDTORes findAuthenticatedUser();
    List<UserDTORes> findAllUsers();
}
