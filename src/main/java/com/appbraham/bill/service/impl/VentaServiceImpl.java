package com.appbraham.bill.service.impl;

import com.appbraham.bill.model.Venta;
import com.appbraham.bill.repository.IGenericRepository;
import com.appbraham.bill.repository.IVentaRepository;
import com.appbraham.bill.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Integer> implements IVentaService {

    @Autowired
    private IVentaRepository repository;

    @Override
    protected IGenericRepository<Venta, Integer> getRepo() {
        return repository;
    }
}
