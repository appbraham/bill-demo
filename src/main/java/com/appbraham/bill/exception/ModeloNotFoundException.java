package com.appbraham.bill.exception;

public class ModeloNotFoundException extends RuntimeException {

    public ModeloNotFoundException(String mensaje){
        super(mensaje);
    }

}
