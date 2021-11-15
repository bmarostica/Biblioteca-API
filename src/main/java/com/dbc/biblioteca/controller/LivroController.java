package com.dbc.biblioteca.controller;

import com.dbc.biblioteca.dto.LivroCreateDTO;
import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
@Slf4j
@Validated
public class LivroController {

    private final LivroService livroService;

    @ApiOperation("Cria um novo livro.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro criado com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PostMapping
    public LivroDTO create(@RequestBody @Valid LivroCreateDTO livroCreateDTO){
        log.info("Criando livro...");
        LivroDTO livro = livroService.create(livroCreateDTO);
        log.info("Livro criado com sucesso!");

        return livro;
    }

    @ApiOperation("Mostra uma lista com todos os livros.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping
    public List<LivroDTO> list(){
        return livroService.list();
    }

    @ApiOperation("Retorna uma lista de livros de acordo com a busca.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/byname")
    public List<LivroDTO> findByName(@RequestParam("titulo") String titulo){
        return livroService.findByName("%" + titulo + "%");
    }

    @ApiOperation("Retorna um livro de acordo com o ID informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/{id}")
    public LivroDTO listById(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Buscando livro...");
        LivroDTO livroDTO = livroService.getById(id);
        log.info("Livro localizado com sucesso!");
        return livroDTO;
    }

    @ApiOperation("Atualiza um livro existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/{id}")
    public LivroDTO update(@PathVariable("id") Integer id,
                           @RequestBody @Valid LivroCreateDTO livroCreateDTO) throws RegraDeNegocioException {
        log.info("Atualizando livro...");
        LivroDTO livroDTO = livroService.update(id, livroCreateDTO);
        log.info("Livro atualizado com sucesso");
        return livroDTO;
    }

    @ApiOperation("Deleta um livro existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro deletado com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Deletando livro...");
        livroService.delete(id);
        log.info("Livro deletado com sucesso!");
    }

}
