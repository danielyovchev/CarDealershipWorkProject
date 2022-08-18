package com.project.data.crud.service;

import com.project.api.model.carBuyModel.CreateCarRequest;
import com.project.data.crud.exception.CarAlreadyExistsException;
import com.project.data.crud.interfaces.CreateCarService;
import com.project.data.db.entity.Car;
import com.project.data.db.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCarServiceImpl implements CreateCarService {
    private final CarRepository carRepository;

    public CreateCarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Long addCar(CreateCarRequest createCarRequest) {
        if(carRepository.existsByVin(createCarRequest.getVin())){
            throw new CarAlreadyExistsException();
        }
        final Double priceMultiplier = 1.20;
        Car car = new Car();
        car.setVin(createCarRequest.getVin());
        car.setMake(createCarRequest.getMake());
        car.setModel(createCarRequest.getModel());
        if(createCarRequest.getFuel().equals("regular unleaded")){
            car.setFuel("petrol");
        }
        else {
            car.setFuel("diesel");
        }
        car.setMileage(createCarRequest.getMileage());
        car.setPrice(createCarRequest.getPrice()*priceMultiplier);
        car.setStatus("available");
        car.setColour(createCarRequest.getColour());
        car.setYear(createCarRequest.getYear());
        carRepository.save(car);
        return car.getId();
    }
}
