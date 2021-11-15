package com.dbc.biblioteca.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "LIVRO")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIVRO")
    private Integer idLivro;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "EDITORA")
    private String editora;

    @Column(name = "NR_PAGINAS")
    private Integer numeroDePaginas;

    @Enumerated
    @Column(name = "FORMATO")
    private Formato formato;

    @Enumerated
    @Column(name = "IDIOMA")
    private Idioma idioma;

    @Enumerated
    @Column(name = "STATUS_LIVRO")
    private StatusLivro statusLivro;

    @JsonIgnore
    @OneToMany(mappedBy = "livroEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmprestimoEntity> emprestimosLivro;
}
