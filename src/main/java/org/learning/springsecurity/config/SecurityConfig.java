package org.learning.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.enums.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
            // Definir endpoints publicos o privados
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(publicEndpoints()).permitAll()
                    .requestMatchers(userEndpoints()).hasAnyAuthority(RoleEnum.USER.name(), RoleEnum.ADMIN.name(), RoleEnum.SUPER_ADMIN.name())
                    .requestMatchers(adminEndpoints()).hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SUPER_ADMIN.name())
                    .requestMatchers(superAdminEndpoints()).hasAuthority(RoleEnum.SUPER_ADMIN.name())
                    .anyRequest().authenticated()) // Any other needs to be authenticated
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    private RequestMatcher publicEndpoints(){
        PathPatternRequestMatcher.Builder builder = PathPatternRequestMatcher.withDefaults();

        return new OrRequestMatcher(List.of(
                builder.matcher(HttpMethod.POST, "/api/auth/register"),
                builder.matcher(HttpMethod.POST, "/api/auth/login")
        ));
    }

    private RequestMatcher userEndpoints(){
        PathPatternRequestMatcher.Builder builder = PathPatternRequestMatcher.withDefaults();

        return new OrRequestMatcher(List.of(
                builder.matcher(HttpMethod.GET, "/api/users/me")
        ));
    }

    private RequestMatcher adminEndpoints(){
        PathPatternRequestMatcher.Builder builder = PathPatternRequestMatcher.withDefaults();

        return new OrRequestMatcher(List.of(
                builder.matcher(HttpMethod.GET, "/api/users")
        ));
    }

    private RequestMatcher superAdminEndpoints(){
        PathPatternRequestMatcher.Builder builder = PathPatternRequestMatcher.withDefaults();

        return new OrRequestMatcher(List.of(
                builder.matcher(HttpMethod.POST, "/api/auth/register/admin"),
                builder.matcher(HttpMethod.GET, "/api/users/full")
        ));
    }
}
