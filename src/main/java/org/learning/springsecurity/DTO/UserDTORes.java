package org.learning.springsecurity.DTO;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTORes {
    private String name;
    private String surname;
    private String email;
    private LocalDate createdAt;
}
