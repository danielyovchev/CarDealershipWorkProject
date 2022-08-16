package com.project.data.domain.mapper;

import com.project.api.model.CarViewModel;
import com.project.data.domain.model.CarDomainModel;
import org.springframework.stereotype.Service;

@Service
public class DomainToResponseMapper {
    public CarViewModel mapCar(CarDomainModel car){
        return CarViewModel.builder()
                .vin(car.getVin())
                .make(car.getMake())
                .model(car.getModel())
                .fuel(car.getFuel())
                .mileage(car.getMileage())
                .price(car.getPrice())
                .status(car.getStatus())
                .year(car.getYear())
                .displacement(car.getDisplacement())
                .drivenWheels(car.getDrivenWheels())
                .economy(car.getEconomy())
                .gears(car.getGears())
                .horsepower(car.getHorsepower())
                .torque(car.getTorque())
                .transmission(car.getTransmission())
                .build();
    }
}
