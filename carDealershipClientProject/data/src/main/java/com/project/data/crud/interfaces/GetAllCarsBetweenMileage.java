package com.project.data.crud.interfaces;

import com.project.data.domain.model.CarListDomainResponse;

public interface GetAllCarsBetweenMileage {
    CarListDomainResponse getByMileageBetween(Integer start, Integer end);
}
