package com.project.core.processor;

import com.project.api.model.carsByParam.FuelRequest;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.db.entity.Car;
import com.project.data.domain.mapper.DomainToResponseMapper;
import com.project.data.domain.model.CarDomainModel;
import com.project.data.domain.model.CarListDomainResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllCarsByFuelOperationCoreTest {
    @Mock
    DomainToResponseMapper mapper;
    @Mock
    GetAllCarsByFuel getAllCarsByFuel;
    @InjectMocks
    private GetAllCarsByFuelOperationCore getAllCarsByFuelOperationCore;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        when(getAllCarsByFuel.getAllCarsByFuel("petrol")).thenReturn(CarListDomainResponse
                .builder()
                .carDomainModelList(List.of(car1))
                .build());
    }

    @Test
    void process() {
        assertEquals(getAllCarsByFuelOperationCore.process(new FuelRequest("petrol")).get().getCarList().get(0), 1);
    }
}