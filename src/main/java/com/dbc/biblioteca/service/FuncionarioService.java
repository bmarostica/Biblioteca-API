package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.FuncionarioCreateDTO;
import com.dbc.biblioteca.dto.FuncionarioDTO;
import com.dbc.biblioteca.entity.FuncionarioEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final ObjectMapper objectMapper;

    public FuncionarioDTO create(FuncionarioCreateDTO funcionarioCreateDTO) throws RegraDeNegocioException{
        FuncionarioEntity entity = objectMapper.convertValue(funcionarioCreateDTO, FuncionarioEntity.class);
        FuncionarioEntity criado = funcionarioRepository.create(entity);
        FuncionarioDTO dto = objectMapper.convertValue(criado, FuncionarioDTO.class);
        return dto;
    }

    public List<FuncionarioDTO> list() {
        return funcionarioRepository.list().stream()
                .map(funcionarioEntity -> objectMapper.convertValue(funcionarioEntity, FuncionarioDTO.class))
                .collect(Collectors.toList());
    }

    public FuncionarioDTO update(Integer id, FuncionarioCreateDTO funcionarioCreateDTO) throws RegraDeNegocioException {
        FuncionarioEntity entity = objectMapper.convertValue(funcionarioCreateDTO, FuncionarioEntity.class);
        FuncionarioEntity atualizado = funcionarioRepository.update(id, entity);
        FuncionarioDTO dto = objectMapper.convertValue(atualizado, FuncionarioDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        funcionarioRepository.delete(id);
    }
}
