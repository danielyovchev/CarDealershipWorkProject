package com.project.data.db.repository;

import com.project.data.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    List<Car> findAllByFuel(String fuel);
    List<Car> findAllByColour(String colour);
    List<Car> findAllByMileageIsLessThan(Integer mileage);
    List<Car> findAllByMake(String make);
    boolean existsByVin(String vin);
    Optional<Car> findByVin(String vin);
}
