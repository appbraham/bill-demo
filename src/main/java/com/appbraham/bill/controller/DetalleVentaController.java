package com.appbraham.bill.controller;

import com.appbraham.bill.exception.ModeloNotFoundException;
import com.appbraham.bill.model.DetalleVenta;
import com.appbraham.bill.service.IDetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/detalleventas")
public class DetalleVentaController {
    
    @Autowired
    private IDetalleVentaService service;

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() throws Exception{

        List<DetalleVenta>lista = service.listar();

        return new ResponseEntity<List<DetalleVenta>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> listarPorId(@PathVariable Integer id) throws Exception{
        DetalleVenta detalleVenta = service.listarPorId(id);

        if(detalleVenta == null){
            throw new ModeloNotFoundException("ID no encontrado: " +id);
        }

        return new ResponseEntity<DetalleVenta>(detalleVenta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody DetalleVenta DetalleVenta) throws Exception{
        DetalleVenta detalleVenta = service.registrar(DetalleVenta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(detalleVenta.getIdDetalleVenta()).toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
        DetalleVenta detalleVenta = service.listarPorId(id);
        if(detalleVenta.getIdDetalleVenta() == null){
            throw new ModeloNotFoundException("El DetalleVenta no existe, ID: " +id);
        }

        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
