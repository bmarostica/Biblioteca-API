package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Integer> {

    @Query(value = "select * " +
            "from LIVRO " +
            "where upper(titulo) like upper(:titulo) "
            ,nativeQuery = true
    )
    List<LivroEntity> findByName(String titulo);

}
