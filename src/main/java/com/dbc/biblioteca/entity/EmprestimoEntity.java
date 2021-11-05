package com.dbc.biblioteca.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoEntity {
    private Integer idEmprestimo;
    private Integer idClienteEmprestimo;
    private Integer idLivroEmprestimo;
    private Integer idFuncionarioEmprestimo;
}
