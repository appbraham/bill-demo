package com.appbraham.bill.controller;

import com.appbraham.bill.exception.ModeloNotFoundException;
import com.appbraham.bill.model.Persona;
import com.appbraham.bill.service.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IPersonaService service;

    @GetMapping
    public ResponseEntity<List<Persona>> listar() throws Exception{

        List<Persona>lista = service.listar();

        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> listarPorId(@PathVariable Integer id) throws Exception{
        Persona persona = service.listarPorId(id);

        if(persona == null){
            throw new ModeloNotFoundException("ID no encontrado: " +id);
        }

        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Persona persona) throws Exception{
        Persona per = service.registrar(persona);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(per.getIdPersona()).toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
        Persona per = service.listarPorId(id);
        if(per.getIdPersona() == null){
            throw new ModeloNotFoundException("La Persona no existe, ID: " +id);
        }

        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
