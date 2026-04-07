package org.learning.springsecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.learning.springsecurity.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_users")
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private String surname;
    @NonNull
    @Column(unique = true, nullable = false)
    private String email;
    @NonNull
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDate updatedAt;
    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private RoleEnum roleEnum;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRoleEnum().name()));
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
