package com.example.underwriting.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.underwriting.enums.LoanStatus;
import com.example.underwriting.enums.ProductType;
import jakarta.persistence.*;
import jakarta.persistence.PreUpdate;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "loan_application")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID applicantId;

    @Column(nullable = false, length = 100)
    private String applicantName;

    @Column(nullable = false, length = 150)
    private String applicantEmail;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal loanAmount;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal propertyValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus currentStatus = LoanStatus.SUBMITTED;

    private UUID assignedUnderwriterId; // nullable

    @Column(nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }
}
