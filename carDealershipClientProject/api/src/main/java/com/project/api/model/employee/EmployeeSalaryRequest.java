package com.project.api.model.employee;

import com.project.api.base.OperationInput;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
@Getter @Setter
public class EmployeeSalaryRequest implements OperationInput {
    private Long id;
    private Integer year;
    private Integer month;
}
