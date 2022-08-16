package com.project.data.crud.interfaces;

import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.CarListResponse;

public interface GetAllCarsByFuel {
    CarListResponse getAllCarsByFuel(String fuel);
}
