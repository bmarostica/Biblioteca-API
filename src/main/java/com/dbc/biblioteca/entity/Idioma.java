package com.dbc.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Idioma {
    PORTUGUES(1),
    INGLES(2),
    ESPANHOL(3);

    private Integer lingua;

    Idioma(Integer lingua) {
        this.lingua = lingua;
    }

    public Integer getLingua() {
        return lingua;
    }

    public static Idioma ofIdioma(Integer i) {
        return Arrays.stream(Idioma.values())
                .filter(idioma -> idioma.getLingua().equals(i))
                .findFirst()
                .get();
    }

    public int toValue(){
        return ordinal();
    }
}
