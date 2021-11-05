package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class LivroRepository {
    private List<LivroEntity> livros = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public LivroRepository() {
        livros.add(new LivroEntity(COUNTER.incrementAndGet(), "Harry Potter e o Prisioneiro de Azkaban", "J.K. Rowling", "Rocco", 317, 0, 0, 0));
        livros.add(new LivroEntity(COUNTER.incrementAndGet(), "Senhor dos Anéis: As Duas Torres ", "J.R.R. Tolkien", "HarperCollins", 464, 1, 0, 0));
        livros.add(new LivroEntity(COUNTER.incrementAndGet(), "Fundação", "Isaac Asimov", "Aleph", 320, 0, 0, 0));
        livros.add(new LivroEntity(COUNTER.incrementAndGet(), "Bruxa Natural", "Arin Murphy-Hiscock", "Darkside", 256, 1, 0, 0));
    }

    public LivroEntity create(LivroEntity livroEntity) {
        livroEntity.setIdLivro(COUNTER.incrementAndGet());
        livros.add(livroEntity);
        return livroEntity;
    }

    public List<LivroEntity> list() {
        return livros;
    }

    public List<LivroEntity> listByName(String titulo) {
        return livros.stream()
                .filter(livro -> StringUtils.containsIgnoreCase(livro.getTitulo(), titulo))
                .collect(Collectors.toList());
    }

    public LivroEntity update(Integer id, LivroEntity livroAtualizar) throws RegraDeNegocioException {
        LivroEntity livroRecuperado = livros.stream()
                .filter(livro -> livro.getIdLivro().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Livro não localizado"));

        livroRecuperado.setTitulo(livroAtualizar.getTitulo());
        livroRecuperado.setAutor(livroAtualizar.getAutor());
        livroRecuperado.setEditora(livroAtualizar.getEditora());
        livroRecuperado.setNumeroDePaginas(livroAtualizar.getNumeroDePaginas());
        livroRecuperado.setFormato(livroAtualizar.getFormato());
        livroRecuperado.setIdioma(livroAtualizar.getIdioma());

        return livroRecuperado;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        LivroEntity livroRecuperado = livros.stream()
                .filter(livro -> livro.getIdLivro().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Livro não localizado"));

        livros.remove(livroRecuperado);
    }


}
