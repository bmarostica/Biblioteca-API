package com.dbc.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TipoCliente {

    COMUM(1),
    PREMIUM(2);

    private Integer tipo;

    TipoCliente(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static TipoCliente ofTipo(Integer t) {
        return Arrays.stream(TipoCliente.values())
                .filter(tipo -> tipo.getTipo().equals(t))
                .findFirst()
                .get();
    }

    public int toValue(){
        return ordinal();
    }
}


