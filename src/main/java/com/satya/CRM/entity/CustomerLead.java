package com.satya.CRM.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_lead")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomerLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Column(nullable = false)
    private String customerName;

    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile must be 10 digits")
    @Column(nullable = false)
    private String mobile;

    private String alternateNumber;

    @Email(message = "Email should be valid")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lead_type_id", nullable = false)
    private LeadType leadType;

    private String city;
    private String address;
    
    @Column(columnDefinition = "TEXT")
    private String requirement;
    
    private String leadSource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_executive_id")
    private User assignedExecutive;

    @Column(columnDefinition = "TEXT")
    private String discussionDetails;

    private LocalDate visitDate;
    private LocalDate nextFollowUpDate;

    // Status: New, Contacted, Interested, Follow Up, Visit Scheduled, Negotiation, Closed Won, Closed Lost, Not Interested.
    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status;

    // Priority: Hot, Warm, Cold, Not a Customer.
    @NotBlank(message = "Priority is required")
    @Column(nullable = false)
    private String priority;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        if (this.status == null) this.status = "New";
    }
}
