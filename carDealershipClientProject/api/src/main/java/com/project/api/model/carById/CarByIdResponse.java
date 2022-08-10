package com.project.api.model.carById;

import com.project.api.base.OperationResult;
import lombok.*;

@Getter @Setter(AccessLevel.PRIVATE) @Builder @ToString
public class CarByIdResponse implements OperationResult {
    private String make;
    private String model;
}
