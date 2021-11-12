package com.dbc.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {
    private Integer idEmprestimo;
    private List<LivroDTO> livros;
    private FuncionarioDTO funcionarioDTO;
    private ContaClienteDTO contaClienteDTO;

}
