package com.project.data.crud.interfaces;

import com.project.api.model.carsByParam.CarListResponse;

public interface GetAllCarsBetweenMileage {
    CarListResponse getByMileageBetween(Integer start, Integer end);
}
