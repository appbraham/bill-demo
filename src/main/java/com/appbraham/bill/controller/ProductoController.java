package com.appbraham.bill.controller;

import com.appbraham.bill.exception.ModeloNotFoundException;
import com.appbraham.bill.model.Producto;
import com.appbraham.bill.service.IProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    IProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() throws Exception {
        List<Producto> lista = service.listar();
        return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{idProducto}")
    public EntityModel<Producto> listarPorId(@PathVariable("idProducto") Integer id) throws Exception {
        Producto producto = service.listarPorId(id);

        if(producto == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
        }

        EntityModel<Producto> recurso = EntityModel.of(producto);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
        recurso.add(linkTo.withRel("productoRecurso"));

        return recurso;
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto) throws Exception {
        Producto nuevoProducto = service.registrar(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoProducto.getIdProducto()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) throws Exception {
        Producto nuevoProducto = service.modificar(producto);
        return new ResponseEntity<Producto>(nuevoProducto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) throws Exception {
        Producto producto = service.listarPorId(id);

        if (producto == null) throw new ModeloNotFoundException("El producto no existe, ID: " + id);

        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


}
