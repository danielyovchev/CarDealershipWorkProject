package com.project.data.db.repository;

import com.project.data.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long id);
    Optional<Employee> findById(Long id);
}
