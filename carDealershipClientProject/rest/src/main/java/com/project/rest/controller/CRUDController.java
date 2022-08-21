package com.project.rest.controller;

import com.project.api.base.Error;
import com.project.api.model.carsByParam.*;
import com.project.api.operation.*;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CRUDController {
    private final GetAllCarsOperation getAllCarsOperation;
    private final GetAllCarsByFuelOperation getAllCarsByFuel;
    private final GetAllCarsByMakeOperation getAllCarsByMake;
    private final GetAllCarsByColourOperation getAllCarsByColour;
    private final GetAllCarsBetweenMileageOperation getAllCarsBetweenMileage;
    public CRUDController(GetAllCarsOperation getAllCarsOperation, GetAllCarsByFuelOperation getAllCarsByFuel, GetAllCarsByMakeOperation getAllCarsByMake, GetAllCarsByColourOperation getAllCarsByColour, GetAllCarsBetweenMileageOperation getAllCarsBetweenMileage) {
        this.getAllCarsOperation = getAllCarsOperation;
        this.getAllCarsByFuel = getAllCarsByFuel;
        this.getAllCarsByMake = getAllCarsByMake;
        this.getAllCarsByColour = getAllCarsByColour;
        this.getAllCarsBetweenMileage = getAllCarsBetweenMileage;
    }
    @GetMapping("/getAllCars")
    public ResponseEntity<?> getAllCars(AllCarRequest allCarRequest){
        Either<Error, CarListResponse> result = getAllCarsOperation.process(allCarRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/getAllCarsByMake")
    public ResponseEntity<?> getByMake(@RequestBody CarMakeRequest carMakeRequest){
        Either<Error, CarListResponse> result = getAllCarsByMake.process(carMakeRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/allByFuel")
    public ResponseEntity<?> getByFuel(@RequestBody FuelRequest fuel){
        Either<Error, CarListResponse> result = getAllCarsByFuel.process(fuel);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/allByColour")
    public ResponseEntity<?> getByColour(@RequestBody CarColourRequest carColourRequest){
        Either<Error, CarListResponse> result = getAllCarsByColour.process(carColourRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/allBetweenMileage")
    public ResponseEntity<?> getBetweenMileage(@RequestBody MileageRequest mileageRequest){
        Either<Error, CarListResponse> result = getAllCarsBetweenMileage.process(mileageRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
