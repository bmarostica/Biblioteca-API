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

    @NotBlank
    @ApiModelProperty(value = "Tipo do Cliente -> 0 para comum e 1 para premium")
    @Min(0)
    @Max(1)
    private Integer tipoCliente;

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Nome do cliente")
    private String nome;

    @NotBlank
    @ApiModelProperty(value = "Telefone do Cliente")
    private String telefone;

    @Email
    @NotNull
    @ApiModelProperty(value = "Email do Cliente")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "Livro do Cliente")
    private LivroEntity livro;

    @NotBlank
    @ApiModelProperty(value = "Status do Cliente -> 1 para OK, 2 para CANCELADO e 3 para BLOQUEADO")
    private StatusCliente status;

    @NotBlank
    @ApiModelProperty(value = "Quantidade de pontos fidelidade do cliente")
    private Integer pontosFidelidade;
}