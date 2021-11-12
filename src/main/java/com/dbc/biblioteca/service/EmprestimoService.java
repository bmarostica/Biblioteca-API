package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.EmprestimoCreateDTO;
import com.dbc.biblioteca.dto.EmprestimoDTO;
import com.dbc.biblioteca.entity.*;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.ContaClienteRepository;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.dbc.biblioteca.repository.FuncionarioRepository;
import com.dbc.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final ObjectMapper objectMapper;
    private final ContaClienteService contaClienteService;
    private final FuncionarioService funcionarioService;
    private final LivroService livroService;
    private final LivroRepository livroRepository;
    private final ContaClienteRepository contaClienteRepository;

    public List<EmprestimoDTO> list() {
        return emprestimoRepository.findAll().stream()
                .map(emprestimo -> {
                    EmprestimoDTO dto = objectMapper.convertValue(emprestimo, EmprestimoDTO.class);
                    try {
                        dto.setContaClienteDTO(contaClienteService.getById(emprestimo.getIdClienteEmprestimo()));
                        dto.setFuncionarioDTO(funcionarioService.getById(emprestimo.getIdFuncionarioEmprestimo()));
                        dto.setLivroDTO(livroService.getById(emprestimo.getIdLivroEmprestimo()));
                    } catch (RegraDeNegocioException e) {
                        e.printStackTrace();
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public EmprestimoDTO getById(Integer id) throws RegraDeNegocioException {
        EmprestimoEntity entity = emprestimoRepository.getById(id);
        EmprestimoDTO dto = objectMapper.convertValue(entity, EmprestimoDTO.class);
        dto.setContaClienteDTO(contaClienteService.getById(entity.getIdClienteEmprestimo()));
        dto.setFuncionarioDTO(funcionarioService.getById(entity.getIdFuncionarioEmprestimo()));
        dto.setLivroDTO(livroService.getById(entity.getIdLivroEmprestimo()));

        return dto;
    }

    public EmprestimoDTO create(EmprestimoCreateDTO emprestimoCreateDTO) throws RegraDeNegocioException {
        LivroEntity livro = livroRepository.getById(emprestimoCreateDTO.getIdLivroEmprestimo());
        ContaClienteEntity cliente = contaClienteRepository.getById(emprestimoCreateDTO.getIdClienteEmprestimo());
        if (livro.getStatusLivro() == StatusLivro.INDISPONIVEL) {
            throw new RegraDeNegocioException("Livro já está emprestado.");
        } else if (cliente.getStatus() == StatusCliente.BLOQUEADO || cliente.getStatus() == StatusCliente.CANCELADO) {
            throw new RegraDeNegocioException("Cliente bloqueado ou cancelado.");
        } else {
            livro.setStatusLivro(StatusLivro.INDISPONIVEL);
        }
        EmprestimoEntity entity = objectMapper.convertValue(emprestimoCreateDTO, EmprestimoEntity.class);
        EmprestimoEntity emprestimoCriado = emprestimoRepository.save(entity);
        EmprestimoDTO dto = objectMapper.convertValue(emprestimoCriado, EmprestimoDTO.class);
        dto.setContaClienteDTO(contaClienteService.getById(entity.getIdClienteEmprestimo()));
        dto.setFuncionarioDTO(funcionarioService.getById(entity.getIdFuncionarioEmprestimo()));
        dto.setLivroDTO(livroService.getById(entity.getIdLivroEmprestimo()));

        if (cliente.getTipoCliente() == TipoCliente.PREMIUM) {
            cliente.setPontosFidelidade(10);
        }

        return dto;
    }

    public EmprestimoDTO update(Integer id, EmprestimoCreateDTO emprestimoCreateDTO) throws RegraDeNegocioException {
        EmprestimoEntity entity = objectMapper.convertValue(emprestimoCreateDTO, EmprestimoEntity.class);
        getById(id);
        EmprestimoEntity atualizado = emprestimoRepository.save(entity);
        EmprestimoDTO dto = objectMapper.convertValue(atualizado, EmprestimoDTO.class);
        dto.setContaClienteDTO(contaClienteService.getById(entity.getIdClienteEmprestimo()));
        dto.setFuncionarioDTO(funcionarioService.getById(entity.getIdFuncionarioEmprestimo()));
        dto.setLivroDTO(livroService.getById(entity.getIdLivroEmprestimo()));

        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        LivroEntity livro = livroRepository.getById(emprestimoRepository.getById(id).getIdLivroEmprestimo());
        livro.setStatusLivro(StatusLivro.DISPONIVEL);
        //emprestimoRepository.delete(livro);
    }

}
