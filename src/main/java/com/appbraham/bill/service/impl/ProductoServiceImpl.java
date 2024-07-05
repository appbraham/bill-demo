package com.appbraham.bill.service.impl;

import com.appbraham.bill.model.Producto;
import com.appbraham.bill.repository.IGenericRepository;
import com.appbraham.bill.repository.IProductoRepository;
import com.appbraham.bill.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements IProductoService {

    @Autowired
    private IProductoRepository repository;

    @Override
    protected IGenericRepository<Producto, Integer> getRepo() {
        return repository;
    }
}
