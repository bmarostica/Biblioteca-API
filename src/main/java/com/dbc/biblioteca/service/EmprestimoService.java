package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.EmprestimoCreateDTO;
import com.dbc.biblioteca.dto.EmprestimoDTO;
import com.dbc.biblioteca.entity.EmprestimoEntity;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.EmprestimoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<EmprestimoDTO> list() {
        return emprestimoRepository.list().stream()
                .map(emprestimo -> objectMapper.convertValue(emprestimo, EmprestimoDTO.class))
                .collect(Collectors.toList());
    }

    public EmprestimoDTO getById(Integer id) throws RegraDeNegocioException {
        EmprestimoEntity entity = emprestimoRepository.getById(id);
        EmprestimoDTO dto = objectMapper.convertValue(entity, EmprestimoDTO.class);
        return dto;
    }

    public EmprestimoDTO crete(EmprestimoCreateDTO emprestimoCreateDTO) throws RegraDeNegocioException {
        EmprestimoEntity emprestimoEntity = objectMapper.convertValue(emprestimoCreateDTO, EmprestimoEntity.class);
        EmprestimoEntity emprestimoCriado = emprestimoRepository.create(emprestimoEntity);
        EmprestimoDTO emprestimoDTO = objectMapper.convertValue(emprestimoCriado, EmprestimoDTO.class);
        return emprestimoDTO;
    }

    public void delete(Integer id) throws Exception {
        emprestimoRepository.delete(id);
    }

}
