package com.project.api.model.carById;

import com.project.api.base.OperationInput;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarByIdRequest implements OperationInput {
    private Integer id;
}
