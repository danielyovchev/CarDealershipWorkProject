package com.project.data.db.repository;

import com.project.data.db.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    boolean existsById(Long id);
}
