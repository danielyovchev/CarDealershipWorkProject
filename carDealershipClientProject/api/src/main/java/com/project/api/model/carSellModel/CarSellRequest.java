package com.project.api.model.carSellModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.api.base.OperationInput;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter @Builder @EqualsAndHashCode
public class CarSellRequest implements OperationInput {
    private String vin;
    private String dealType;
    private Integer months;
    private Integer employeeId;
    private Integer customerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
