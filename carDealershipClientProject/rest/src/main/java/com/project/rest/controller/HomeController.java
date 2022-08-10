package com.project.rest.controller;

import com.project.api.base.Error;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final CarSellOperation carSellOperation;
    public HomeController(CarSellOperation carSellOperation) {
        this.carSellOperation = carSellOperation;
    }
    @PostMapping("/sellCar")
    public ResponseEntity<?> sellCar(@RequestBody CarSellRequest carSellRequest){
        Either<Error, CarSellResponse> result = carSellOperation.process(carSellRequest);
        /*if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode).body(result.getLeft().getMessage());
        }*/
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
