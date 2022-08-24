package com.project.api.model.carsByParam;

import com.project.api.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class CarMakeRequest implements OperationInput {
    private String make;
}
