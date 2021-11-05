package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.EmprestimoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EmprestimoRepository {
    private static List<EmprestimoEntity> listaEmprestimoEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public EmprestimoRepository() {
        listaEmprestimoEntities.add(new EmprestimoEntity(COUNTER.incrementAndGet(), 11, 2, 1,
                "Harry Potter", "Lucas", "André"));
        listaEmprestimoEntities.add(new EmprestimoEntity(COUNTER.incrementAndGet(), 2, 3, 2,
                "Senhor dos Anéis", "João", "Felipe"));
        listaEmprestimoEntities.add(new EmprestimoEntity(COUNTER.incrementAndGet(), 133, 1, 3,
                "Fundação", "Fabrício", "Jonas"));
    }

    public List<EmprestimoEntity> list() {
        return listaEmprestimoEntities;
    }

    public EmprestimoEntity getById(Integer id) throws Exception {
        return listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Não encontrado."));
    }

    public EmprestimoEntity create(EmprestimoEntity emprestimoEntity) {
        emprestimoEntity.setIdEmprestimo(COUNTER.incrementAndGet());
        listaEmprestimoEntities.add(emprestimoEntity);
        return emprestimoEntity;
    }

    public EmprestimoEntity update(Integer id, EmprestimoEntity emprestimoAtualizar) throws Exception {
        EmprestimoEntity emprestimoRecuperado = listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Não encontrado."));

        emprestimoRecuperado.setIdClienteEmprestimo(emprestimoAtualizar.getIdClienteEmprestimo());
        emprestimoRecuperado.setIdLivroEmprestimo(emprestimoAtualizar.getIdLivroEmprestimo());
        emprestimoRecuperado.setIdFuncionarioEmprestimo(emprestimoAtualizar.getIdFuncionarioEmprestimo());
        emprestimoRecuperado.setTituloLivroEmprestimo(emprestimoAtualizar.getTituloLivroEmprestimo());
        emprestimoRecuperado.setNomeClienteEmprestimo(emprestimoAtualizar.getNomeClienteEmprestimo());
        emprestimoRecuperado.setNomeFuncionarioEmprestimo(emprestimoAtualizar.getNomeFuncionarioEmprestimo());
        return emprestimoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        EmprestimoEntity emprestimoRecuperado = listaEmprestimoEntities.stream()
                .filter(emprestimo -> emprestimo.getIdEmprestimo().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Não encontrado."));
        listaEmprestimoEntities.remove(emprestimoRecuperado);
    }
}
