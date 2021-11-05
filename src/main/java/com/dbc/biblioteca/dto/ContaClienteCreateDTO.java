package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.TipoCliente;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaClienteCreateDTO {

    @NotNull
    @ApiModelProperty(value = "Tipo do Cliente -> 0 para comum e 1 para premium")
    @Min(0)
    @Max(1)
    private Integer tipoCliente;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do cliente")
    private String nome;

    @NotNull
    @ApiModelProperty(value = "Telefone do Cliente")
    private String telefone;

    @Email
    @NotNull
    @ApiModelProperty(value = "Email do Cliente")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Livro do Cliente")
    private LivroEntity livro;

    @NotNull
    @ApiModelProperty(value = "Status do Cliente -> 1 para OK, 2 para CANCELADO e 3 para BLOQUEADO")
    private StatusCliente status;

    @NotNull
    @ApiModelProperty(value = "Quantidade de pontos fidelidade do cliente")
    private Integer pontosFidelidade;
}