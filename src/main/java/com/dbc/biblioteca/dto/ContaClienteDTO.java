package com.dbc.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "idCliente", "nome" }, alphabetic = true)
public class ContaClienteDTO extends ContaClienteCreateDTO{
    private Integer idCliente;
}
