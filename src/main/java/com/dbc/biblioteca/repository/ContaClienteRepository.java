package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.TipoCliente;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ContaClienteRepository  {
    private static List<ContaClienteEntity> listaClientesEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private static LivroDTO livro1 = new LivroDTO();

    public ContaClienteRepository() {
        listaClientesEntities.add(new ContaClienteEntity(COUNTER.incrementAndGet(), 1, "Marcelo", "998855220", "marcelo@email.com", 1, 25));
        listaClientesEntities.add(new ContaClienteEntity(COUNTER.incrementAndGet(), 0, "Bianca", "805552650", "bianca@email.com", 0, 50));
        listaClientesEntities.add(new ContaClienteEntity(COUNTER.incrementAndGet(), 1, "David", "923312320", "david@email.com", 0, 50));
    }

    public List<ContaClienteEntity> list() {
        return listaClientesEntities;
    }

    public ContaClienteEntity getById(Integer id) throws RegraDeNegocioException {
        return listaClientesEntities.stream()
                .filter(contaCliente -> contaCliente.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada"));
    }

    public ContaClienteEntity create(ContaClienteEntity contaClienteEntity) {
        contaClienteEntity.setIdCliente(COUNTER.incrementAndGet());
        listaClientesEntities.add(contaClienteEntity);
        return contaClienteEntity;
    }

    public ContaClienteEntity update(Integer id, ContaClienteEntity contaAtualizar) throws RegraDeNegocioException {
        ContaClienteEntity contaRecuperada = listaClientesEntities.stream()
                .filter(cliente -> cliente.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada"));
        contaRecuperada.setTipoCliente(contaAtualizar.getTipoCliente());
        contaRecuperada.setNome(contaAtualizar.getNome());
        contaRecuperada.setTelefone(contaAtualizar.getTelefone());
        contaRecuperada.setEmail(contaAtualizar.getEmail());
        contaRecuperada.setStatus(contaAtualizar.getStatus());
        contaRecuperada.setPontosFidelidade(contaAtualizar.getPontosFidelidade());
        return contaRecuperada;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ContaClienteEntity contaRecuperada = listaClientesEntities.stream()
                .filter(conta -> conta.getIdCliente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Conta não encontrada"));
        listaClientesEntities.remove(contaRecuperada);
    }
}