package org.learning.springsecurity.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationDTOReq {
    private String email;
    private String password;
}
