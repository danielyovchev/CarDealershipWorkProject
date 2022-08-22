package com.project.data.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE) @Builder
public class CarListDomainResponse {
    private List<CarDomainModel> carDomainModelList;
}
