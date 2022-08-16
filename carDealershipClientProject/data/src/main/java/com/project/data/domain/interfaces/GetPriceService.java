package com.project.data.domain.interfaces;

import com.price.api.model.PriceRequest;
import com.price.api.model.PriceResponse;

public interface GetPriceService {
    PriceResponse getPriceFromService(PriceRequest priceRequest);
}