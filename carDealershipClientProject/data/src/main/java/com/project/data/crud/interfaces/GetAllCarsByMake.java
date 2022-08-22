package com.project.data.crud.interfaces;

import com.project.data.domain.model.CarListDomainResponse;

public interface GetAllCarsByMake {
    CarListDomainResponse getAllCarsByMake(String make);
}
