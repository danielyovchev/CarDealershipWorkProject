package com.project.data.db.repository;

import com.project.data.db.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    boolean existsById(Long id);
    List<Sales> findAllByEmployeeIdAndDateBetween(Long emplId, LocalDate start, LocalDate end);
}
