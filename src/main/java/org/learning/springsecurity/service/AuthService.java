package org.learning.springsecurity.service;

import org.learning.springsecurity.DTO.AuthDTORes;
import org.learning.springsecurity.DTO.AuthenticationDTOReq;
import org.learning.springsecurity.DTO.RegisterDTOReq;
import org.learning.springsecurity.DTO.UserDTORes;
import org.learning.springsecurity.enums.RoleEnum;

public interface AuthService {
    UserDTORes register(RegisterDTOReq request, RoleEnum roleEnum);
    AuthDTORes authenticate(AuthenticationDTOReq request);
}
