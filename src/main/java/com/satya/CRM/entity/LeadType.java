package com.satya.CRM.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "lead_type")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lead type name is required")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
