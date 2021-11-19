package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.RegraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraRepository extends JpaRepository<RegraEntity, Integer> {
}
