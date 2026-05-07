package com.example.underwriting.repository;


import com.example.underwriting.entity.LoanApplication;
import com.example.underwriting.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {
    List<LoanApplication> findByCurrentStatus(LoanStatus status);
    List<LoanApplication> findByAssignedUnderwriterId(UUID underwriterId);
}
