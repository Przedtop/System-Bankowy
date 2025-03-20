package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.PreHandleWebInceptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreHandleWebInceptorRepo extends JpaRepository<PreHandleWebInceptor, Long> {
    long countByDateBetween(String startDate, String endDate);
    long countByDateBetweenAndStatus(String startDate, String endDate, String status);
}