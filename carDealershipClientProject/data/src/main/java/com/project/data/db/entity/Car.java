package com.project.data.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vin;
    private String make;
    private String model;
    private String fuel;
    private Integer mileage;
    private Double price;
    private String status;
    private String colour;
    private Integer year;

    public Car(String vin, String make, String model, String fuel, Integer mileage, Double price, String status, String colour, Integer year) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.fuel = fuel;
        this.mileage = mileage;
        this.price = price;
        this.status = status;
        this.colour = colour;
        this.year = year;
    }
}
