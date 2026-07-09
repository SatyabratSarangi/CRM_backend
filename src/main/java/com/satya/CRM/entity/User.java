package com.satya.CRM.entity;

import jakarta.persistence.*;
import lombok.*;
import com.satya.CRM.enums.Role;

@Entity
@Table(name = "crm_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
