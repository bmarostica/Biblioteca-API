package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.LivroCreateDTO;
import com.dbc.biblioteca.dto.LivroDTO;
import com.dbc.biblioteca.entity.LivroEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final ObjectMapper objectMapper;

    public LivroDTO create(LivroCreateDTO livroCreateDTO) {
        LivroEntity livroEntity = objectMapper.convertValue(livroCreateDTO, LivroEntity.class);
        LivroEntity livroCriado = livroRepository.create(livroEntity);

        LivroDTO livroDTO = objectMapper.convertValue(livroCriado, LivroDTO.class);

        return livroDTO;
    }

    public List<LivroDTO> list() {
        return livroRepository.list().stream()
                .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                .collect(Collectors.toList());
    }

    public List<LivroDTO> listByName(String titulo) {
        return livroRepository.listByName(titulo).stream()
                .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                .collect(Collectors.toList());
    }

    public LivroDTO update(Integer id, LivroCreateDTO livroCreateDTO) throws RegraDeNegocioException {
        LivroEntity livroEntity = objectMapper.convertValue(livroCreateDTO, LivroEntity.class);
        LivroEntity livroAtualizado = livroRepository.update(id, livroEntity);

        LivroDTO livroDTO = objectMapper.convertValue(livroAtualizado, LivroDTO.class);

        return livroDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        livroRepository.delete(id);
    }

}
