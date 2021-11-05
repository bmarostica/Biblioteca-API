package com.dbc.biblioteca.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class FuncionarioCreateDTO {
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Nome do funcionário.")
    private String nome;
    @NotEmpty
    @NotBlank
    @Size(max=11, min=11, message = "Telefone deve conter 11 digitos.")
    @ApiModelProperty(value = "Telefone do funcionário.")
    private String telefone;
    @NotEmpty
    @NotBlank
    @Email
    @ApiModelProperty(value = "E-mail do funcionário")
    private String email;
}
