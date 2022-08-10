package com.project.data.db.repository;

import com.project.data.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
}
