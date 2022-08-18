package com.project.api.model.carBuyModel;

import com.project.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE) @Builder
public class CarBuyResponse implements OperationResult {
    private String message;
}
