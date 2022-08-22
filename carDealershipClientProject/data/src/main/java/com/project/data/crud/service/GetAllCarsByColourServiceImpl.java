package com.project.data.crud.service;

import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByColour;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarListDomainResponse;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByColourServiceImpl implements GetAllCarsByColour {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;

    public GetAllCarsByColourServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
    }

    @Override
    public CarListDomainResponse getByColour(String colour) {
        if(carRepository.findAllByColour(colour).isEmpty()){
            throw new CarNotFoundException();
        }
        return CarListDomainResponse.builder()
                .carDomainModelList(carRepository.findAllByColour(colour)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .toList()).build();
    }
}
