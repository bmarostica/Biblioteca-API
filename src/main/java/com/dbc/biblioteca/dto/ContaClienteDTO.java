package com.dbc.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "idCliente", "nome" }, alphabetic = true)
public class ContaClienteDTO extends ContaClienteCreateDTO{
    private Integer idCliente;

    @ApiModelProperty(value = "Quantidade de pontos fidelidade do cliente")
    private Integer pontosFidelidade;
}
