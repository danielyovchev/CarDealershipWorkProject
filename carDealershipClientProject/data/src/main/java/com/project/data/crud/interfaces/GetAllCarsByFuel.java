package com.project.data.crud.interfaces;

import com.project.data.domain.model.CarListDomainResponse;

public interface GetAllCarsByFuel {
    CarListDomainResponse getAllCarsByFuel(String fuel);
}
