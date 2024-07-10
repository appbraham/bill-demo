package com.appbraham.bill.controller;

import com.appbraham.bill.exception.ModeloNotFoundException;
import com.appbraham.bill.model.Venta;
import com.appbraham.bill.service.IVentaService;
import com.appbraham.bill.service.IVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    IVentaService service;

    @GetMapping
    public ResponseEntity<List<Venta>> listar() throws Exception {
        List<Venta> lista = service.listar();
        return new ResponseEntity<List<Venta>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{idVenta}")
    public EntityModel<Venta> listarPorId(@PathVariable("idVenta") Integer id) throws Exception {
        Venta venta = service.listarPorId(id);

        if(venta == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        EntityModel<Venta> recurso = EntityModel.of(venta);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("ventaRecurso"));

        return recurso;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta) throws Exception {
        Venta nuevaVenta = service.registrar(venta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevaVenta.getIdVenta()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta venta) throws Exception {
        Venta nuevaVenta = service.modificar(venta);
        return new ResponseEntity<Venta>(nuevaVenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) throws Exception {
        Venta venta = service.listarPorId(id);

        if (venta == null) throw new ModeloNotFoundException("La venta no existe, ID: " + id);

        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
