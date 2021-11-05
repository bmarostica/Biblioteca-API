package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.EmprestimoEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EmprestimoRepository {
    private static List<EmprestimoEntity> listaEmprestimoEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public List<EmprestimoEntity> list() {
        return listaEmprestimoEntities;
    }

    public EmprestimoEntity getById(Integer id) throws RegraDeNegocioException {
        return listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Não encontrado."));
    }

    public EmprestimoEntity create(EmprestimoEntity emprestimoEntity) {
        emprestimoEntity.setIdEmprestimo(COUNTER.incrementAndGet());
        listaEmprestimoEntities.add(emprestimoEntity);
        return emprestimoEntity;
    }

    public EmprestimoEntity update(Integer id, EmprestimoEntity emprestimoAtualizar) throws RegraDeNegocioException {
        EmprestimoEntity emprestimoRecuperado = listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Não encontrado."));

        emprestimoRecuperado.setIdClienteEmprestimo(emprestimoAtualizar.getIdClienteEmprestimo());
        emprestimoRecuperado.setIdLivroEmprestimo(emprestimoAtualizar.getIdLivroEmprestimo());
        emprestimoRecuperado.setIdFuncionarioEmprestimo(emprestimoAtualizar.getIdFuncionarioEmprestimo());
        return emprestimoRecuperado;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        EmprestimoEntity emprestimoRecuperado = listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Não encontrado."));
        listaEmprestimoEntities.remove(emprestimoRecuperado);
    }
}
