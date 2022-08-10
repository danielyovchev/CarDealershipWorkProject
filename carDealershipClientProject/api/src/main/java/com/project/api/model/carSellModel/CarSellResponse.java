package com.project.api.model.carSellModel;

import com.project.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@ToString
public class CarSellResponse implements OperationResult {
    private Double price;
    private String car;
}
