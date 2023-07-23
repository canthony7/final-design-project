package com.crud.management.repository;

import com.crud.management.pojo.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByFeeType(String feeType);

    List<Fee> findByFeeTypeAndCreateTimeBetween(String feeType, LocalDate startDate, LocalDate endDate);

    List<Fee> findByCreateTimeBetween(LocalDate startDate, LocalDate endDate);

    List<Fee> findByProject_Id(Long projectId);

    Fee findByFeeTypeAndProject_Id(String feeType, Long projectId);

}
