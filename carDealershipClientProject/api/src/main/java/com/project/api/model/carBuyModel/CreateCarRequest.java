package com.project.api.model.carBuyModel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter(AccessLevel.PRIVATE) @Builder
public class CreateCarRequest {
    private String vin;
    private String make;
    private String model;
    private String fuel;
    private Integer mileage;
    private Double price;
    private String colour;
    private Integer year;
}
