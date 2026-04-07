package org.learning.springsecurity.mapper;

import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.entity.User;

public class UserMapper {
    public static UserDTORes userToUserDTORes(User user){
        return UserDTORes.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
