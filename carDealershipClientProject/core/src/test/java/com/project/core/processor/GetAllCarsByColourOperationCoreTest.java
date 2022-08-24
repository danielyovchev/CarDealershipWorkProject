package com.project.core.processor;

import com.project.api.model.carsByParam.CarColourRequest;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.FuelRequest;
import com.project.data.crud.interfaces.GetAllCarsByColour;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.domain.mapper.DomainToResponseMapper;
import com.project.data.domain.model.CarDomainModel;
import com.project.data.domain.model.CarListDomainResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllCarsByColourOperationCoreTest {
    @Mock
    private DomainToResponseMapper mapper;
    @Mock
    private GetAllCarsByColour getAllCarsByColour;
    @InjectMocks
    private GetAllCarsByColourOperationCore getAllCarsByColourOperationCore;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process() {
        CarDomainModel car1 = new CarDomainModel();
        car1.setMake("Ford");
        car1.setFuel("petrol");
        car1.setDisplacement(1560);
        car1.setEconomy(8.5);
        car1.setGears(5);
        car1.setMileage(165406);
        car1.setStatus("available");
        car1.setDrivenWheels("front");
        car1.setTorque(150);
        car1.setPrice(3500.0);
        car1.setTransmission("manual");
        car1.setHorsepower(100);
        car1.setYear(1995);
        car1.setModel("Escort");
        car1.setVin("1FASP11J6TW112004");
        CarListDomainResponse carListDomainResponse =   CarListDomainResponse
                .builder()
                .carDomainModelList(List.of(car1))
                .build();
        when(getAllCarsByColour.getByColour("white")).thenReturn(carListDomainResponse);
        CarColourRequest carColourRequest = new CarColourRequest("white");
        assertEquals(getAllCarsByColourOperationCore.process(carColourRequest).get().getClass(), CarListResponse.class);
    }
}