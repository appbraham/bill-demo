package com.appbraham.bill.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name = "fk_venta_detalle_venta"))
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "fk_producto_detalle_venta"))
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
