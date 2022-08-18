package com.project.data.crud.interfaces;

import com.project.api.model.carsByParam.CarListResponse;

public interface GetAllCarsByColour {
    CarListResponse getByColour(String colour);
}
