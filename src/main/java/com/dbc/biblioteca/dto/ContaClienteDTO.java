package com.dbc.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaClienteDTO extends ContaClienteCreateDTO{
    private Integer idCliente;
}
