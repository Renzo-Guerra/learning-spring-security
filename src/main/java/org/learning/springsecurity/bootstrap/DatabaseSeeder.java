package org.learning.springsecurity.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleSeeder roleSeeder;
    private final UserSeeder userSeeder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleSeeder.seed();
        userSeeder.seed();
    }

}
