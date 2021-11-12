package com.dbc.biblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FUNCIONARIO")
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
}
