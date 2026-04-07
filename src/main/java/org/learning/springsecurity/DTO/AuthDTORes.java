package org.learning.springsecurity.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthDTORes {
    private String token;
}
