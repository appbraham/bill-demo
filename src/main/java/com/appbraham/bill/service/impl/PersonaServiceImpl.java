package com.appbraham.bill.service.impl;

import com.appbraham.bill.model.Persona;
import com.appbraham.bill.repository.IPersonaRepository;
import com.appbraham.bill.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    IPersonaRepository repository;

    @Override
    public Persona registrar(Persona obj) {
        return repository.save(obj);
    }

    @Override
    public Persona modificar(Persona obj) {
        return repository.save(obj);
    }

    @Override
    public List<Persona> listar() {
        return repository.findAll();
    }

    @Override
    public Persona listarPorId(Integer id) {
        Optional<Persona> lista = repository.findById(id);
        return lista.isPresent() ? lista.get() : new Persona();
    }

    @Override
    public boolean eliminar(Integer id) {
        return false;
    }
}
