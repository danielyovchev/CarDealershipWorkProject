package com.project.api.model.employee;

import com.project.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter(AccessLevel.PRIVATE) @Builder
public class EmployeeSalaryResponse implements OperationResult {
    private String firstName;
    private String lastName;
    private Double bonus;
    private Double salary;
    private String month;
    private String year;
}
