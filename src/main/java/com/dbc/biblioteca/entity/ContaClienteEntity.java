package com.dbc.biblioteca.entity;

import com.dbc.biblioteca.dto.LivroDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "cliente")
public class ContaClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "tipo_cliente")
    @Enumerated
    private TipoCliente tipoCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "status_cliente")
    @Enumerated
    private StatusCliente status;

    @Column(name = "pontos_fidelidade")
    private Integer pontosFidelidade;

    @JsonIgnore
    @OneToMany(mappedBy = "contaClienteEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmprestimoEntity> emprestimosCliente;

}
