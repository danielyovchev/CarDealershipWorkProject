package com.project.data.domain.service;

import com.price.api.feign.PriceFeign;
import com.price.api.model.PriceRequest;
import com.price.api.model.PriceResponse;
import com.project.data.domain.interfaces.GetPriceService;
import org.springframework.stereotype.Service;

@Service
public class GetPriceServiceImpl implements GetPriceService {
    private final PriceFeign priceFeign;

    public GetPriceServiceImpl(PriceFeign priceFeign) {
        this.priceFeign = priceFeign;
    }

    @Override
    public PriceResponse getPriceFromService(PriceRequest priceRequest) {
        return priceFeign.getPrice(priceRequest);
    }
}
