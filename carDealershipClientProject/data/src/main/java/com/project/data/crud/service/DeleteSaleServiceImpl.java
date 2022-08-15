package com.project.data.crud.service;

import com.project.data.crud.exception.IdNotExistingException;
import com.project.data.crud.interfaces.DeleteSaleService;
import com.project.data.db.repository.SalesRepository;

public class DeleteSaleServiceImpl implements DeleteSaleService {
    private final SalesRepository salesRepository;

    public DeleteSaleServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public String deleteSale(Long id) {
        if(!salesRepository.existsById(id)){
            throw new IdNotExistingException();
        }
        salesRepository.deleteById(id);
        return "Sale deleted successfully";
    }
}
