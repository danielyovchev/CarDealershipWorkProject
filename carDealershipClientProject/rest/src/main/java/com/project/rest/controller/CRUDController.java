package com.project.rest.controller;

import com.project.api.base.Error;
import com.project.api.model.carsByParam.AllCarRequest;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.FuelRequest;
import com.project.api.model.carsByParam.MileageRequest;
import com.project.api.operation.GetAllCarsBetweenMileageOperation;
import com.project.api.operation.GetAllCarsByFuelOperation;
import com.project.api.operation.GetAllCarsOperation;
import com.project.data.crud.interfaces.*;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CRUDController {
    private final GetAllCarsOperation getAllCarsOperation;
    private final GetAllCarsByFuelOperation getAllCarsByFuel;
    private final GetAllCarsByMake getAllCarsByMake;
    private final GetAllCarsByColour getAllCarsByColour;
    private final GetAllCarsBetweenMileageOperation getAllCarsBetweenMileage;
    public CRUDController(GetAllCarsOperation getAllCarsOperation, GetAllCarsByFuelOperation getAllCarsByFuel, GetAllCarsByMake getAllCarsByMake, GetAllCarsByColour getAllCarsByColour, GetAllCarsBetweenMileageOperation getAllCarsBetweenMileage) {
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
    @GetMapping("/getAllCarsByMake")
    public CarListResponse getByMake(@RequestParam String make){
        return getAllCarsByMake.getAllCarsByMake(make);
    }
    @PostMapping("/allByFuel")
    public ResponseEntity<?> getByFuel(@RequestBody FuelRequest fuel){
        Either<Error, CarListResponse> result = getAllCarsByFuel.process(fuel);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @GetMapping("/allByColour")
    public CarListResponse getByColour(@RequestParam String colour){
        return getAllCarsByColour.getByColour(colour);
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
