package com.dbc.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDTO {
    @NotEmpty
    @NotBlank
    private String login;
    @NotEmpty
    @NotBlank
    private String senha;
    @NotNull
    private List<Integer> regras;
}
