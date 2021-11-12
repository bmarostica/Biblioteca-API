package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ApiModelProperty(value = "Tipo do Cliente -> comum e premium")
    private TipoCliente tipoCliente;

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
    @ApiModelProperty(value = "Status do Cliente -> ATIVO, CANCELADO e BLOQUEADO")
    private StatusCliente status;

}