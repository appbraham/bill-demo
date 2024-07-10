package com.appbraham.bill.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(name = "importe", nullable = false)
    private Long importe;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "idPersona", nullable = false, foreignKey = @ForeignKey(name = "fk_venta_persona"))
    private Persona persona;

    @OneToMany(mappedBy = "venta", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DetalleVenta> detalleVentaLista;

}
