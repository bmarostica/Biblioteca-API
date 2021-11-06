package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.entity.StatusCliente;
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
    @ApiModelProperty(value = "Status do Cliente -> 0 para OK, 1 para CANCELADO e 2 para BLOQUEADO")
    @Min(0)
    @Max(2)
    private Integer status;

    @NotNull
    @ApiModelProperty(value = "Quantidade de pontos fidelidade do cliente")
    private Integer pontosFidelidade;
}