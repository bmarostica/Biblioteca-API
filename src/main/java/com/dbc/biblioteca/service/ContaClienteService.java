package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.ContaClienteCreateDTO;
import com.dbc.biblioteca.dto.ContaClienteDTO;
import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.PlanosDeAssinatura;
import com.dbc.biblioteca.repository.ContaClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaClienteService  implements PlanosDeAssinatura {
    private final ContaClienteRepository contaClienteRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ContaClienteDTO> list() {
        return contaClienteRepository.list().stream()
                .map(conta -> objectMapper.convertValue(conta, ContaClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ContaClienteDTO getById(Integer id) throws Exception {
        ContaClienteEntity entity = contaClienteRepository.getById(id);
        ContaClienteDTO dto = objectMapper.convertValue(entity, ContaClienteDTO.class);
        return dto;
    }

    public ContaClienteDTO create(ContaClienteCreateDTO contaClienteCreateDTO) throws Exception {
        ContaClienteEntity contaClienteEntity = objectMapper.convertValue(contaClienteCreateDTO, ContaClienteEntity.class);
        ContaClienteEntity contaCriada = contaClienteRepository.create(contaClienteEntity);
        ContaClienteDTO contaClienteDTO = objectMapper.convertValue(contaCriada, ContaClienteDTO.class);
        return contaClienteDTO;
    }

    public ContaClienteDTO update(Integer id, ContaClienteCreateDTO contaClienteCreateDTO) throws Exception {
        ContaClienteEntity entity = objectMapper.convertValue(contaClienteCreateDTO, ContaClienteEntity.class);
        ContaClienteEntity update = contaClienteRepository.update(id, entity);
        ContaClienteDTO dto = objectMapper.convertValue(update, ContaClienteDTO.class);
        return dto;
    }

    public void delete(Integer id) throws Exception {
        contaClienteRepository.delete(id);
    }


    @Override
    public void cobrarMensalidade(double valor) {
        ContaClienteEntity cliente = new ContaClienteEntity();
        if(cliente.getPontosFidelidade() > 0) {
            cliente.setPontosFidelidade((int) (cliente.getPontosFidelidade() - valor));
        }
    }
}
