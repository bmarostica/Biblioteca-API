package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.ContaClienteEntity;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteEntity, Integer> {

}