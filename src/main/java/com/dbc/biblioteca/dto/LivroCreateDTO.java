package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.Formato;
import com.dbc.biblioteca.entity.Idioma;
import com.dbc.biblioteca.entity.StatusLivro;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroCreateDTO {

    @NotBlank
    @ApiModelProperty(value = "Nome do livro")
    private String titulo;

    @NotBlank
    @ApiModelProperty(value = "Nome do autor")
    private String autor;

    @NotBlank
    @ApiModelProperty(value = "Nome da editora")
    private String editora;

    @NotNull
    @ApiModelProperty(value = "Quantidade de páginas do livro")
    private Integer numeroDePaginas;

    @NotNull
    @ApiModelProperty(value = "Formato do livro -> Brochura ou Capa dura")
    private Formato formato;

    @NotNull
    @ApiModelProperty(value = "Idioma do livro -> Português, Inglês ou Espanhol")
    private Idioma idioma;

    @NotNull
    @ApiModelProperty(value = "Status do livro -> Disponível ou indisponível")
    private StatusLivro statusLivro;
}
