package com.dbc.biblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "emprestimo" )
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo")
    private Integer idEmprestimo;

    @Column(name = "id_cliente")
    private Integer idClienteEmprestimo;

    @Column(name = "id_livro")
    private Integer idLivroEmprestimo;

    @Column(name = "id_funcionario")
    private Integer idFuncionarioEmprestimo;
}
