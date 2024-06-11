package com.appbraham.bill.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Size(min = 2, message = "El nombre debe de tener 2 caracteres como mínimo")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Size(min = 2, message = "La marca debe de tener 2 caracteres como mínimo")
    @Column(name = "marca", nullable = false, length = 150)
    private String marca;
}
