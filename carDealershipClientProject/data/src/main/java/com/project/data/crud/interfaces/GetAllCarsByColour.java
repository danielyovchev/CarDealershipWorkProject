package com.project.data.crud.interfaces;

import com.project.data.domain.model.CarListDomainResponse;

public interface GetAllCarsByColour {
    CarListDomainResponse getByColour(String colour);
}
