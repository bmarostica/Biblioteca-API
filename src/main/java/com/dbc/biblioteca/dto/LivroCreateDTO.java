package com.dbc.biblioteca.dto;

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
    @ApiModelProperty(value = "Formato do livro -> 0 para brochura e 1 para capa dura")
    private Integer formato;

    @NotNull
    @ApiModelProperty(value = "Idioma do livro -> 0 para Português, 1 para Inglês e 2 para Espanhol")
    private Integer idioma;

    @NotNull
    @ApiModelProperty(value = "Status do livro -> 0 para disponível e 1 para indisponível")
    private Integer statusLivro;
}
