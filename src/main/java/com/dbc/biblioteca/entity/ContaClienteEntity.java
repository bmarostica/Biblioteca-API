package com.dbc.biblioteca.entity;

import com.dbc.biblioteca.dto.LivroDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaClienteEntity {
    private Integer idCliente;
    private Integer tipoCliente;
    private String nome;
    private String telefone;
    private String email;
    private LivroDTO livro;
    private StatusCliente status;
    private Integer pontosFidelidade;

}
