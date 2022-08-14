package com.project.data.domain.interfaces;

import com.project.data.domain.model.CarDomainModel;

public interface MapCarFromApiService {
    CarDomainModel getCar(String vin);
}
