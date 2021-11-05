package com.dbc.biblioteca.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaClienteEntity {
    private Integer idCliente;
    private TipoCliente tipoCliente;
    private String nome;
    private String telefone;
    private String email;
    private LivroEntity livro;
    private StatusCliente status;
    private Integer pontosFidelidade;

}
