package com.dbc.biblioteca.controller;

import com.dbc.biblioteca.dto.FuncionarioCreateDTO;
import com.dbc.biblioteca.dto.FuncionarioDTO;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.service.FuncionarioService;
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
@RequestMapping("/funcionario")
@RequiredArgsConstructor
@Validated
@Slf4j
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @ApiOperation(value = "Cria um novo funcionário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inconsistentes inseridos."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PostMapping
    public FuncionarioDTO create(@RequestBody @Valid FuncionarioCreateDTO funcionarioCreateDTO) throws RegraDeNegocioException {
        log.info("Criando funcionario...");
        FuncionarioDTO dto = funcionarioService.create(funcionarioCreateDTO);
        log.info("Funcionário criado com sucesso.");
        return dto;
    }


    @ApiOperation(value = "Lista todos os funcionários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @GetMapping
    public List<FuncionarioDTO> list() {
        return funcionarioService.list();
    }


    @ApiOperation(value = "Atualiza um funcionário através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inconsistentes inseridos ou funcionario não encontrado."),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @PutMapping("/{idFuncionario}")
    public FuncionarioDTO update(@PathVariable("idFuncionario") Integer id,
                                 @RequestBody FuncionarioCreateDTO funcionarioCreateDTO) throws RegraDeNegocioException {
        log.info("Atualizando funcionario...");
        FuncionarioDTO dto = funcionarioService.update(id, funcionarioCreateDTO);
        log.info("Funcionario atualizado com sucesso.");
        return dto;
    }


    @ApiOperation(value = "Deleta um funcionário através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deu certo! O comando funcionou."),
            @ApiResponse(code = 400, message = "Há dados inconsistentes inseridos ou funcionario não encontrado"),
            @ApiResponse(code = 500, message = "Problema interno no sistema."),
    })
    @DeleteMapping("/{idFuncionario}")
    public void delete(@PathVariable("idFuncionario") Integer id) throws RegraDeNegocioException {
        log.info("Deletando funcionario...");
        funcionarioService.delete(id);
        log.info("Funcionario deletado com sucesso.");
    }

}
