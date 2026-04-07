package org.learning.springsecurity.bootstrap;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.entity.Role;
import org.learning.springsecurity.enums.RoleEnum;
import org.learning.springsecurity.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements Seeder {
    private final RoleRepository roleRepository;

    @Override
    public void seed() {
        RoleEnum[] roleNames = new RoleEnum[]{ RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };

        Map<RoleEnum, String> roleDescriptions = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach(roleName ->{
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, ()->{
                Role newRole = Role.builder()
                        .name(roleName)
                        .description(roleDescriptions.get(roleName))
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .build();

                roleRepository.save(newRole);
            });
        });
    }
}
