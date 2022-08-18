package com.project.data.crud.interfaces;

import com.project.api.model.carsByParam.CarListResponse;

public interface GetAllCarsByMake {
    CarListResponse getAllCarsByMake(String make);
}
