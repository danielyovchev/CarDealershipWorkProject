package com.project.api.model.carBuyModel;

import com.project.api.base.OperationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter(AccessLevel.PRIVATE) @Builder
public class CarBuyRequest implements OperationInput {
    private String vin;
    private Double price;
}
