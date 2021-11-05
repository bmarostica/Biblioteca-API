package com.dbc.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private Integer idEmprestimo;
    private LivroDTO livroDTO;
    private FuncionarioDTO funcionarioDTO;
    private ContaClienteDTO contaClienteDTO;

}
