package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.PreHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreHandlerRepo extends JpaRepository<PreHandler, Long> {
    long countByDateBetween(String startDate, String endDate);
    long countByDateBetweenAndStatus(String startDate, String endDate, String status);
}