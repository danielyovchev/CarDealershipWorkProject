package com.project.api.model.carsByParam;

import com.project.api.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class FuelRequest implements OperationInput {
    private String fuel;
}
