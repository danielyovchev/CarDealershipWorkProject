package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.employee.EmployeeSalaryRequest;
import com.project.api.model.employee.EmployeeSalaryResponse;
import io.vavr.control.Either;

public interface GetSalaryOperation extends OperationProcessor<EmployeeSalaryRequest, EmployeeSalaryResponse> {
    @Override
    Either<Error, EmployeeSalaryResponse> process(EmployeeSalaryRequest input);
}
