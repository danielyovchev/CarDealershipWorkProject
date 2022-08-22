package com.project.data.domain.model;

import lombok.*;


@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class CarDomainModel {
    private String vin;
    private String make;
    private String model;
    private Integer year;
    private String fuel;
    private Double economy;
    private Integer displacement;
    private Integer horsepower;
    private Integer torque;
    private String transmission;
    private Integer gears;
    private String drivenWheels;
    private Integer mileage;
    private Double price;
    private String status;
}
