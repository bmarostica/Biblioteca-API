package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteEntity, Integer> {

//    @Query(value = "UPDATE CLIENTE SET PONTOS_FIDELIDADE = :pontos WHERE ID_CLIENTE = :idCliente", nativeQuery = true)

//    @Query(value = "select c.pontos_fidelidade " +
//                    "from cliente c " +
//                    "where id_cliente = ?1"
//            , nativeQuery = true)
//    public Integer retornaPontosFidelidadePeloIdDoCliente(Integer idCliente);

}