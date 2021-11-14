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
    private final FuncionarioRepository funcionarioRepository;

    private EmprestimoEntity findById(Integer id) throws RegraDeNegocioException {
        EmprestimoEntity entity = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Emprestimo não encontrado."));
        return entity;
    }

    public List<EmprestimoDTO> list() {
        return emprestimoRepository.findAll().stream()
                .map(emprestimo -> {
                    EmprestimoDTO dto = objectMapper.convertValue(emprestimo, EmprestimoDTO.class);
                    try {
                        dto.setContaClienteDTO(contaClienteService.getById(emprestimo.getContaClienteEntity().getIdCliente()));
                        dto.setFuncionarioDTO(funcionarioService.getById(emprestimo.getFuncionarioEntity().getIdFuncionario()));
                        dto.setLivroDTO(livroService.getById(emprestimo.getLivroEntity().getIdLivro()));
                    } catch (RegraDeNegocioException e) {
                        e.printStackTrace();
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public EmprestimoDTO getById(Integer id) throws RegraDeNegocioException {
        findById(id);
        EmprestimoEntity entity = emprestimoRepository.getById(id);
        EmprestimoDTO dto = objectMapper.convertValue(entity, EmprestimoDTO.class);
        dto.setContaClienteDTO(contaClienteService.getById(entity.getContaClienteEntity().getIdCliente()));
        dto.setFuncionarioDTO(funcionarioService.getById(entity.getFuncionarioEntity().getIdFuncionario()));
        dto.setLivroDTO(livroService.getById(entity.getLivroEntity().getIdLivro()));

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
        cliente.setPontosFidelidade(cliente.getPontosFidelidade() + 10);
        contaClienteRepository.save(cliente);
        EmprestimoEntity entity = objectMapper.convertValue(emprestimoCreateDTO, EmprestimoEntity.class);
        entity.setLivroEntity(livroRepository.getById(emprestimoCreateDTO.getIdLivroEmprestimo()));
        entity.setFuncionarioEntity(funcionarioRepository.getById(emprestimoCreateDTO.getIdFuncionarioEmprestimo()));
        entity.setContaClienteEntity(contaClienteRepository.getById(emprestimoCreateDTO.getIdClienteEmprestimo()));
        EmprestimoEntity emprestimoCriado = emprestimoRepository.save(entity);
        EmprestimoDTO dto = objectMapper.convertValue(emprestimoCriado, EmprestimoDTO.class);
        dto.setLivroDTO(livroService.getById(entity.getLivroEntity().getIdLivro()));
        dto.setContaClienteDTO(contaClienteService.getById(entity.getContaClienteEntity().getIdCliente()));
        dto.setFuncionarioDTO(funcionarioService.getById(entity.getFuncionarioEntity().getIdFuncionario()));
        return dto;
    }

    public EmprestimoDTO update(Integer id, EmprestimoCreateDTO emprestimoCreateDTO) throws RegraDeNegocioException {
        EmprestimoEntity emprestimoExistente = findById(id);
        EmprestimoEntity emprestimoNovo = objectMapper.convertValue(emprestimoCreateDTO, EmprestimoEntity.class);
        emprestimoNovo.setIdEmprestimo(id);
        emprestimoNovo.setLivroEntity(livroRepository.getById(emprestimoCreateDTO.getIdLivroEmprestimo()));
        emprestimoNovo.setFuncionarioEntity(funcionarioRepository.getById(emprestimoCreateDTO.getIdFuncionarioEmprestimo()));
        emprestimoNovo.setContaClienteEntity(contaClienteRepository.getById(emprestimoCreateDTO.getIdClienteEmprestimo()));


        //SE TROCAR O CLIENTE REMOVE O VALOR DE PONTOS DO CLIENTE ANTIGO E ADICIONAR PARA O NOVO
        if (emprestimoExistente.getContaClienteEntity().getIdCliente() != emprestimoNovo.getContaClienteEntity().getIdCliente()) {
            emprestimoExistente.getContaClienteEntity().setPontosFidelidade(emprestimoExistente.getContaClienteEntity().getPontosFidelidade() -10);
            emprestimoNovo.getContaClienteEntity().setPontosFidelidade(emprestimoNovo.getContaClienteEntity().getPontosFidelidade() +10);
        }
        EmprestimoEntity atualizado = emprestimoRepository.save(emprestimoNovo);
        EmprestimoDTO dto = objectMapper.convertValue(atualizado, EmprestimoDTO.class);
        dto.setContaClienteDTO(contaClienteService.getById(emprestimoNovo.getContaClienteEntity().getIdCliente()));
        dto.setFuncionarioDTO(funcionarioService.getById(emprestimoNovo.getFuncionarioEntity().getIdFuncionario()));
        dto.setLivroDTO(livroService.getById(emprestimoNovo.getLivroEntity().getIdLivro()));

        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        EmprestimoEntity entity = findById(id);
        LivroEntity livro = livroRepository.getById(emprestimoRepository.getById(id).getLivroEntity().getIdLivro());
        livro.setStatusLivro(StatusLivro.DISPONIVEL);
        emprestimoRepository.delete(entity);
    }

}
