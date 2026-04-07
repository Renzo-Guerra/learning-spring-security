package org.learning.springsecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.learning.springsecurity.enums.RoleEnum;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Column(nullable = false)
    private String description;
    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDate updatedAt;


}
