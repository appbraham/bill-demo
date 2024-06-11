package com.appbraham.bill.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    @Size(min = 2, message = "El nombre debe de tener mínimo 2 caracteres")
    @Column(name = "nombres", nullable = false, length = 80)
    private String nombres;

    @Size(min = 2, message = "El apellido debe de tener mínimo 2 caracteres")
    @Column(name = "apellidos", nullable = false, length = 80)
    private String apellidos;

}
