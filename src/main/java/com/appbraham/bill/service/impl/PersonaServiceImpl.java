package com.appbraham.bill.service.impl;

import com.appbraham.bill.model.Persona;
import com.appbraham.bill.repository.IGenericRepository;
import com.appbraham.bill.repository.IPersonaRepository;
import com.appbraham.bill.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements IPersonaService {

    @Autowired
    IPersonaRepository repository;

    @Override
    protected IGenericRepository<Persona, Integer> getRepo() {
        //Esto es para saber cual repository usar en el genérico gracias al @Autowired
        return repository;
    }
}
