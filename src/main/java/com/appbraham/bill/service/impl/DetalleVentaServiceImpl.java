package com.appbraham.bill.service.impl;

import com.appbraham.bill.model.DetalleVenta;
import com.appbraham.bill.repository.IDetalleVentaRepository;
import com.appbraham.bill.repository.IGenericRepository;
import com.appbraham.bill.service.IDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaServiceImpl extends GenericServiceImpl<DetalleVenta, Integer> implements IDetalleVentaService {

    @Autowired()
    IDetalleVentaRepository repository;

    @Override
    protected IGenericRepository getRepo() {
        return repository;
    }
}
