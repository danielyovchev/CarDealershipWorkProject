package com.project.rest.controller;

import com.project.api.base.Error;
import com.project.api.model.carById.CarByIdRequest;
import com.project.api.model.carById.CarByIdResponse;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import com.project.api.operation.GetCarByIdOperation;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final CarSellOperation carSellOperation;
    private final GetCarByIdOperation getCarByIdOperation;
    public HomeController(CarSellOperation carSellOperation, GetCarByIdOperation getCarByIdOperation) {
        this.carSellOperation = carSellOperation;
        this.getCarByIdOperation = getCarByIdOperation;
    }
    @PostMapping("/sellCar")
    public ResponseEntity<?> sellCar(@RequestBody CarSellRequest carSellRequest){
        Either<Error, CarSellResponse> result = carSellOperation.process(carSellRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/getById")
    public ResponseEntity<?> showCar(@RequestBody CarByIdRequest carByIdRequest){
        Either<Error, CarByIdResponse> result = getCarByIdOperation.process(carByIdRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
