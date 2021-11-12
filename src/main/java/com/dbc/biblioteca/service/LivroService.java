package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.LivroCreateDTO;
import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final ObjectMapper objectMapper;

    public LivroDTO create(LivroCreateDTO livroCreateDTO) {
        LivroEntity livroEntity = objectMapper.convertValue(livroCreateDTO, LivroEntity.class);
        LivroEntity livroCriado = livroRepository.save(livroEntity);

        LivroDTO livroDTO = objectMapper.convertValue(livroCriado, LivroDTO.class);

        return livroDTO;
    }

    public List<LivroDTO> list() {
        return livroRepository.findAll().stream()
                .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                .collect(Collectors.toList());
    }

    public List<LivroDTO> findByName(String titulo) {
        return livroRepository.findByName(titulo).stream()
                .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                .collect(Collectors.toList());
    }
    public LivroEntity findById(Integer id) throws RegraDeNegocioException {
        LivroEntity livroEntity = livroRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Livro não localizado"));
        return livroEntity;
    }

    public LivroDTO getById(Integer id) throws RegraDeNegocioException {
        LivroEntity livroEntity = findById(id);
        LivroDTO livroDTO = objectMapper.convertValue(livroEntity, LivroDTO.class);
        return livroDTO;
    }

    public LivroDTO update(Integer id, LivroCreateDTO livroCreateDTO) throws RegraDeNegocioException {
        getById(id);
        LivroEntity livro = objectMapper.convertValue(livroCreateDTO, LivroEntity.class);
        livro.setIdLivro(id);
        LivroEntity livroAtualizado = livroRepository.save(livro);

        LivroDTO livroDTO = objectMapper.convertValue(livroAtualizado, LivroDTO.class);

        return livroDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        LivroEntity livroEntity = findById(id);
        livroRepository.delete(livroEntity);
    }


}
