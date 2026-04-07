package org.learning.springsecurity.bootstrap;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.DTO.RegisterDTOReq;
import org.learning.springsecurity.enums.RoleEnum;
import org.learning.springsecurity.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements Seeder{
    private final AuthService authService;

    @Override
    public void seed() {
        this.seedSuperAdmins();
        this.seedAdmins();
        this.seedUsers();
    }

    private void seedSuperAdmins(){
        List<RegisterDTOReq> superAdmins = List.of(
                RegisterDTOReq.builder().name("superadmin").surname("1").email("superadmin1@gmail.com").password("password123").build()
        );

        superAdmins.forEach(admin -> {
            authService.register(admin, RoleEnum.SUPER_ADMIN);
        });
    }

    private void seedAdmins(){
        List<RegisterDTOReq> admins = List.of(
                RegisterDTOReq.builder().name("admin").surname("1").email("admin1@gmail.com").password("password123").build()
        );

        admins.forEach(admin -> {
            authService.register(admin, RoleEnum.ADMIN);
        });
    }

    private void seedUsers(){
        List<RegisterDTOReq> users = List.of(
                RegisterDTOReq.builder().name("Carlos").surname("Gómez").email("carlos.gomez@gmail.com").password("password123").build(),
                RegisterDTOReq.builder().name("Laura").surname("Martínez").email("laura.martinez@gmail.com").password("password123").build(),
                RegisterDTOReq.builder().name("Matías").surname("López").email("matias.lopez@gmail.com").password("password123").build(),
                RegisterDTOReq.builder().name("Valentina").surname("Rodríguez").email("valentina.rodriguez@gmail.com").password("password123").build(),
                RegisterDTOReq.builder().name("Sebastián").surname("Fernández").email("sebastian.fernandez@gmail.com").password("password123").build()
        );

        users.forEach(user -> {
            authService.register(user, RoleEnum.USER);
        });
    }

}
