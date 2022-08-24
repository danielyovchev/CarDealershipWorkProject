package com.project.core.processor;

import com.example.api.feign.ApiFeignClient;
import com.example.api.model.CarApiResponseModel;
import com.project.api.model.carBuyModel.CarBuyRequest;
import com.project.api.model.carBuyModel.CarBuyResponse;
import com.project.data.crud.interfaces.CreateCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CarBuyOperationCoreTest {
    @Mock
    private CreateCarService createCarService;
    @Mock
    private ApiFeignClient apiFeignClient;
    @InjectMocks
    private CarBuyOperationCore carBuyOperationCore;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process() {
        CarBuyRequest carBuyRequest = CarBuyRequest.builder()
                .price(5450.00)
                .mileage(150000)
                .vin("afasdasd123")
                .build();
        CarApiResponseModel carApiResponseModel = CarApiResponseModel
                .builder()
                .model("Escort")
                .make("Ford")
                .fuel("petrol")
                .build();
        CarBuyResponse carBuyResponse = CarBuyResponse.builder()
                .message("Car " + carApiResponseModel.getMake() + " " + carApiResponseModel.getModel() + " is bought for "
                        + carBuyRequest.getPrice())
                .build();
        Mockito.when(apiFeignClient.getCar(any())).thenReturn(carApiResponseModel);
        Mockito.when(createCarService.addCar(any())).thenReturn(null);
        assertEquals("Car Ford Escort is bought for 5450.0", carBuyOperationCore.process(carBuyRequest).get().getMessage());
    }
    @Test
    void processMileageExcess() {
        CarBuyRequest carBuyRequest = CarBuyRequest.builder()
                .price(5450.00)
                .mileage(450000)
                .vin("afasdasd123")
                .build();
        CarApiResponseModel carApiResponseModel = CarApiResponseModel
                .builder()
                .model("Escort")
                .make("Ford")
                .fuel("petrol")
                .build();
        CarBuyResponse carBuyResponse = CarBuyResponse.builder()
                .message("Car " + carApiResponseModel.getMake() + " " + carApiResponseModel.getModel() + " is bought for "
                        + carBuyRequest.getPrice())
                .build();
        Mockito.when(apiFeignClient.getCar(any())).thenReturn(carApiResponseModel);
        Mockito.when(createCarService.addCar(any())).thenReturn(null);
        assertEquals("Car has done too many kilometers", carBuyOperationCore.process(carBuyRequest).get().getMessage());
    }
}