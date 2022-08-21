package com.project.api.model.carsByParam;

import com.project.api.base.OperationInput;
import lombok.Getter;

@Getter
public class CarMakeRequest implements OperationInput {
    private String make;
}
