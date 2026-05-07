package com.example.underwriting.entity;

import com.example.underwriting.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "application_data")
public class ApplicationData {

    @Id
    @Column(name = "application_id")
    private UUID applicationId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "application_id")
    private LoanApplication loanApplication;

    private int creditScore;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal monthlyIncome;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal monthlyDebt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentStatus employmentStatus;

    private int employmentMonths;

    private boolean bankruptcyHistory;
    private boolean foreclosureHistory;

    @Transient
    public BigDecimal getDti() {
        if (monthlyIncome == null || monthlyIncome.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return monthlyDebt.divide(monthlyIncome, 4, RoundingMode.HALF_UP);
    }

    @Transient
    public BigDecimal getLtv() {
        if (loanApplication == null || loanApplication.getPropertyValue() == null ||
                loanApplication.getPropertyValue().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return loanApplication.getLoanAmount()
                .divide(loanApplication.getPropertyValue(), 4, RoundingMode.HALF_UP);
    }

}
