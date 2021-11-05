package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.FuncionarioEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class FuncionarioRepository {
    private static List<FuncionarioEntity> listaFuncionarios = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();


    public List<FuncionarioEntity> list() {
        return listaFuncionarios;
    }

    public FuncionarioEntity create(FuncionarioEntity funcionarioEntity) {
        funcionarioEntity.setIdFuncionario(COUNTER.incrementAndGet());
        listaFuncionarios.add(funcionarioEntity);
        return funcionarioEntity;
    }

    public FuncionarioEntity update(Integer id, FuncionarioEntity funcionarioAtualizar) throws RegraDeNegocioException {
        FuncionarioEntity funcionarioRecuperado = listaFuncionarios.stream()
                .filter(funcionarioEntity -> funcionarioEntity.getIdFuncionario().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Funcionário não encontrado."));
        funcionarioRecuperado.setNome(funcionarioAtualizar.getNome());
        funcionarioRecuperado.setTelefone(funcionarioAtualizar.getTelefone());
        funcionarioRecuperado.setEmail(funcionarioAtualizar.getEmail());
        return funcionarioRecuperado;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        FuncionarioEntity funcionarioRecuperado = listaFuncionarios.stream()
                .filter(funcionarioEntity -> funcionarioEntity.getIdFuncionario().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Funcionário não encontrado."));
        listaFuncionarios.remove(funcionarioRecuperado);

    }
}
