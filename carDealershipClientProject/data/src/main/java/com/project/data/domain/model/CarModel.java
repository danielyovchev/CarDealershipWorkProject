package com.project.data.domain.model;

import lombok.*;

@Getter @Setter(AccessLevel.PRIVATE) @Builder @ToString
public class CarModel {
    private String vin;
    private String make;
    private String model;
    private String fuel;
    private Integer mileage;
    private Double price;
    private String status;
    private Integer year;
}
