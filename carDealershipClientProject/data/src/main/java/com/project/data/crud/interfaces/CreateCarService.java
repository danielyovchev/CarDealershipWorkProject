package com.project.data.crud.interfaces;

import com.example.api.model.CarApiResponseModel;
import com.project.api.model.carBuyModel.CarBuyRequest;
import com.project.api.model.carBuyModel.CreateCarRequest;
import com.project.data.domain.model.CarDomainModel;

public interface CreateCarService {
    Long addCar(CreateCarRequest createCarRequest);
}
