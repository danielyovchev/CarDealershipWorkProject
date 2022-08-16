package com.project.api.model.carsByParam;

import com.project.api.model.CarViewModel;
import lombok.*;

import java.util.List;
@Getter
@Setter(AccessLevel.PRIVATE) @Builder
public class CarListResponse {
    private List<CarViewModel> carList;
}
