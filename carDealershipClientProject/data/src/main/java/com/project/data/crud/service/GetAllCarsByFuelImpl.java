package com.project.data.crud.service;

import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarListDomainResponse;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByFuelImpl implements GetAllCarsByFuel {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;

    public GetAllCarsByFuelImpl(CarRepository carRepository, MapCarFromApiService carFromApiService) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
    }

    @Override
    public CarListDomainResponse getAllCarsByFuel(String fuel){
        if(carRepository.findAllByFuel(fuel).isEmpty()){
            throw new CarNotFoundException();
        }
        return CarListDomainResponse.builder()
                .carDomainModelList(carRepository.findAllByFuel(fuel)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .toList())
                .build();
    }
}
