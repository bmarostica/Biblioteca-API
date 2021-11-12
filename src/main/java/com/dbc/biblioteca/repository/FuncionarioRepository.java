package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.FuncionarioEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {

}
