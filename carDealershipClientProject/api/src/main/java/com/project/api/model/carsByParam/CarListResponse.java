package com.project.api.model.carsByParam;

import com.project.api.base.OperationResult;
import com.project.api.model.CarViewModel;
import lombok.*;

import java.util.List;
@Getter
@Setter(AccessLevel.PRIVATE) @Builder
public class CarListResponse implements OperationResult {
    private List<CarViewModel> carList;
}
