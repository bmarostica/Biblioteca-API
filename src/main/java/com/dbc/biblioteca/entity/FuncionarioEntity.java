package com.dbc.biblioteca.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioEntity {
    private Integer idFuncionario;
    private String nome;
    private String telefone;
    private String email;
}
