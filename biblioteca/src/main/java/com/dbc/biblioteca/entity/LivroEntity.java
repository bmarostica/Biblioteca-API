package com.dbc.biblioteca.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroEntity {
    private Integer idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private Integer numeroDePaginas;
    private Integer formato;
    private Integer idioma;
    private Integer statusLivro;
}
