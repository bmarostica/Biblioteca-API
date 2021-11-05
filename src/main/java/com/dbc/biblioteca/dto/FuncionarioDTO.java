package com.dbc.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonPropertyOrder(value = { "idFuncionario", "nome" }, alphabetic = true)
public class FuncionarioDTO extends FuncionarioCreateDTO {
    @NotNull
    @ApiModelProperty(value = "Id do funcionário.")
    private Integer idFuncionario;
}
