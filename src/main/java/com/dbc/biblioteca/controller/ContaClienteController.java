package com.dbc.biblioteca.controller;

import com.dbc.biblioteca.dto.ContaClienteCreateDTO;
import com.dbc.biblioteca.dto.ContaClienteDTO;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.service.ContaClienteService;
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
@RequestMapping("/conta")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ContaClienteController {
    private final ContaClienteService contaClienteService;

    @ApiOperation(value = "Lista todas as contas")
    @GetMapping
    public List<ContaClienteDTO> list() {
        return contaClienteService.list();
    }

    @ApiOperation(value = "Busca uma conta por seu ID")
    @GetMapping("/{idCliente}")
    public ContaClienteDTO getById(@PathVariable("idCliente") Integer idCliente) throws RegraDeNegocioException {
        return contaClienteService.getById(idCliente);
    }

    @ApiOperation(value = "Cria uma nova conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta criada com sucesso!"),
            @ApiResponse(code = 400, message = "Conta com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @PostMapping
    public ContaClienteDTO create(@RequestBody @Valid ContaClienteCreateDTO contaClienteCreateDTO) throws RegraDeNegocioException {
        log.info("Iniciando a criação da conta...");
        ContaClienteDTO contaEntityCriado = contaClienteService.create(contaClienteCreateDTO);
        log.info("Conta criada com sucesso!");
        return contaEntityCriado;
    }

    @ApiOperation(value = "Atualiza uma conta pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Conta com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @PutMapping("/{idCliente}")
    public ContaClienteDTO update(@PathVariable("idCliente") Integer id,
                                  @RequestBody @Valid ContaClienteCreateDTO contaClienteCreateDTO) throws RegraDeNegocioException {
        return contaClienteService.update(id, contaClienteCreateDTO);
    }

    @ApiOperation(value = "Delete uma conta pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Conta com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @DeleteMapping("/{idCliente}")
    public void delete(@PathVariable("idCliente") Integer id) throws RegraDeNegocioException {
        contaClienteService.delete(id);
    }
}