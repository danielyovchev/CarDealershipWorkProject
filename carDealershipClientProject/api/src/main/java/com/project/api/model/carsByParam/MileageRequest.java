package com.project.api.model.carsByParam;

import com.project.api.base.OperationInput;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MileageRequest implements OperationInput {
    private Integer start;
    private Integer end;
}
