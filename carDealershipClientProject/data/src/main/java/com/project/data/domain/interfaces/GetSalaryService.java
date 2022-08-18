package com.project.data.domain.interfaces;

import com.salary.api.model.SalaryRequest;
import com.salary.api.model.SalaryResponse;

public interface GetSalaryService {
    SalaryResponse getSalary(SalaryRequest salaryRequest);
}
