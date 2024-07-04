package com.appbraham.bill.service.impl;

import com.appbraham.bill.repository.IGenericRepository;
import com.appbraham.bill.service.IGenericService;

import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T registrar(T obj) throws Exception {
        return getRepo().save(obj);
    }

    @Override
    public T modificar(T obj) throws Exception {
        return getRepo().save(obj);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
