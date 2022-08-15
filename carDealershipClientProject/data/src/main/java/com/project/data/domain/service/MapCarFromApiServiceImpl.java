package com.project.data.domain.service;

import com.example.api.feign.ApiFeignClient;
import com.example.api.model.CarApiResponseModel;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.db.entity.Car;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarDomainModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapCarFromApiServiceImpl implements MapCarFromApiService {
    private final ApiFeignClient apiFeignClient;
    private final CarRepository carRepository;

    public MapCarFromApiServiceImpl(ApiFeignClient apiFeignClient, CarRepository carRepository) {
        this.apiFeignClient = apiFeignClient;
        this.carRepository = carRepository;
    }

    @Override
    public CarDomainModel getCar(String vin) {
        final CarApiResponseModel carApiResponseModel;
        try {
            carApiResponseModel = apiFeignClient.getCar(vin);
        }
        catch (Exception e){
            throw new CarNotFoundException();
        }
        final Optional<Car> carEntity = carRepository.findByVin(vin);
        return CarDomainModel.builder()
                .vin(carEntity.orElseThrow(CarNotFoundException::new).getVin())
                .make(carApiResponseModel.getMake())
                .model(carApiResponseModel.getModel())
                .fuel(carApiResponseModel.getFuel())
                .mileage(carEntity.get().getMileage())
                .price(carEntity.get().getPrice())
                .status(carEntity.get().getStatus())
                .year(carEntity.get().getYear())
                .displacement(carApiResponseModel.getDisplacement())
                .drivenWheels(carApiResponseModel.getDrivenWheels())
                .economy(carApiResponseModel.getEconomy())
                .gears(carApiResponseModel.getGears())
                .horsepower(carApiResponseModel.getHorsepower())
                .torque(carApiResponseModel.getTorque())
                .transmission(carApiResponseModel.getTransmission())
                .build();
    }
}
