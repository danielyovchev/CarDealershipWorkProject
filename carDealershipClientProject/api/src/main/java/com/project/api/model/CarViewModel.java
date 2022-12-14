package com.project.api.model;

import com.project.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE) @Builder
@ToString
public class CarViewModel implements OperationResult {
    private String vin;
    private String make;
    private String model;
    private int year;
    private String fuel;
    private double economy;
    private int displacement;
    private int horsepower;
    private int torque;
    private String transmission;
    private int gears;
    private String drivenWheels;
    private Integer mileage;
    private Double price;
    private String status;
}
