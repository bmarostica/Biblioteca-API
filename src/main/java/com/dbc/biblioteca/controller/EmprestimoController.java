package com.dbc.biblioteca.controller;

import com.dbc.biblioteca.dto.EmprestimoCreateDTO;
import com.dbc.biblioteca.dto.EmprestimoDTO;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.service.EmprestimoService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EmprestimoController {
    private final EmprestimoService emprestimoService;


    @ApiOperation(value = "Lista todos os empréstimos ativos.")
    @GetMapping
    public List<EmprestimoDTO> list() {
        return emprestimoService.list();
    }

    @ApiOperation(value = "Busca um empréstimo pelo seu ID")
    @GetMapping("/{idEmprestimo}")
    public EmprestimoDTO getById(@PathVariable("idEmprestimo") Integer idEmprestimo) throws RegraDeNegocioException {
        return emprestimoService.getById(idEmprestimo);
    }


    @ApiOperation(value = "Cria um novo Empréstimo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empréstimo criado com sucesso!"),
            @ApiResponse(code = 400, message = "Empréstimo com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @PostMapping
    public EmprestimoDTO create(@RequestParam("Id do Funcionário")Integer idFuncionario,
                                @RequestParam("Id do Livro")Integer idLivro,
                                @RequestParam("Id do Cliente")Integer idCliente) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        EmprestimoCreateDTO emprestimoCreateDTO = new EmprestimoCreateDTO();
        emprestimoCreateDTO.setIdClienteEmprestimo(idCliente);
        emprestimoCreateDTO.setIdFuncionarioEmprestimo(idFuncionario);
        emprestimoCreateDTO.setIdLivroEmprestimo(idLivro);
        log.info("Iniciando a criação do Empréstimo...");
        EmprestimoDTO emprestimoEntityCriado = emprestimoService.create(emprestimoCreateDTO);
        log.info("Empréstimo criado com sucesso!");
        return emprestimoEntityCriado;
    }


    @ApiOperation(value = "Atualiza um empréstimo pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empréstimo atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Empréstimo com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @PutMapping("/{idEmprestimo}")
        public EmprestimoDTO update(@PathVariable ("idEmprestimo")Integer id,
                                    @RequestParam("Id do Funcionário")Integer idFuncionario,
                                    @RequestParam("Id do Livro")Integer idLivro,
                                    @RequestParam("Id do Cliente")Integer idCliente) throws RegraDeNegocioException {
        EmprestimoCreateDTO emprestimoCreateDTO = new EmprestimoCreateDTO();
        emprestimoCreateDTO.setIdClienteEmprestimo(idCliente);
        emprestimoCreateDTO.setIdFuncionarioEmprestimo(idFuncionario);
        emprestimoCreateDTO.setIdLivroEmprestimo(idLivro);
        return emprestimoService.update(id, emprestimoCreateDTO);
        }


    @ApiOperation(value = "Faz a devolução do livro e deleta o empréstimo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empréstimo deletado com sucesso!"),
            @ApiResponse(code = 400, message = "Empréstimo com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Exceção no sistema")
    })
    @DeleteMapping("/{idEmprestimo}")
    public void alteraStatusEmpretimo(@PathVariable("idEmprestimo") Integer id) throws RegraDeNegocioException {
        emprestimoService.delete(id);
    }
}

