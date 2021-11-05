package com.dbc.biblioteca.repository;

import com.dbc.biblioteca.entity.LivroEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public LivroEntity create(LivroEntity livroEntity){
        livroEntity.setIdLivro(COUNTER.incrementAndGet());
        livros.add(livroEntity);
        return livroEntity;
    }

    public List<LivroEntity> list(){
        return livros;
    }

    public List<LivroEntity> listByNome(LivroEntity livroEntity){
        return livros.stream()
                .filter(livro -> livroEntity.)

    }




}
