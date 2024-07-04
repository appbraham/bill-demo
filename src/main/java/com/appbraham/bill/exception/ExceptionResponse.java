package com.appbraham.bill.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {

    private LocalDateTime fecha;
    private String mensaje;
    private String detalles;

    public ExceptionResponse(LocalDateTime fecha, String mensaje, String detalles) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

}
