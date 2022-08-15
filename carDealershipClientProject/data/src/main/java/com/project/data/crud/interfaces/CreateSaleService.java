package com.project.data.crud.interfaces;

import com.project.api.model.carSellModel.CarSellRequest;

public interface CreateSaleService {
    Long createSale(CarSellRequest carSellRequest);
}
