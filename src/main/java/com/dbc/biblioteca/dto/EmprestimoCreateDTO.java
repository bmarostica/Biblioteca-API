package com.dbc.biblioteca.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoCreateDTO {

    @NotNull
    @ApiModelProperty(value = "ID do cliente")
    private Integer idClienteEmprestimo;

    @NotNull
    @ApiModelProperty(value = "ID do livro")
    private Integer idLivroEmprestimo;

    @NotNull
    @ApiModelProperty(value = "ID do funcionário")
    private Integer idFuncionarioEmprestimo;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Título do Livro")
    private String tituloLivroEmprestimo;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do cliente")
    private String nomeClienteEmprestimo;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do funcionário")
    private String nomeFuncionarioEmprestimo;
}
