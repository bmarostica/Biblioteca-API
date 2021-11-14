package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.ContaClienteCreateDTO;
import com.dbc.biblioteca.dto.ContaClienteDTO;
import com.dbc.biblioteca.entity.ContaClienteEntity;
import com.dbc.biblioteca.entity.PlanosDeAssinatura;
import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.TipoCliente;
import com.dbc.biblioteca.exceptions.RegraDeNegocioException;
import com.dbc.biblioteca.repository.ContaClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaClienteService  implements PlanosDeAssinatura {
    private final ContaClienteRepository contaClienteRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public ContaClienteEntity findById(Integer id) throws RegraDeNegocioException {
        ContaClienteEntity entity = contaClienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado."));
        return entity;
    }

    public List<ContaClienteDTO> list() {
        return contaClienteRepository.findAll().stream()
                .map(conta -> objectMapper.convertValue(conta, ContaClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ContaClienteDTO getById(Integer id) throws RegraDeNegocioException {
        ContaClienteEntity entity = findById(id);
        ContaClienteDTO dto = objectMapper.convertValue(entity, ContaClienteDTO.class);
        return dto;
    }

    public ContaClienteDTO create(ContaClienteCreateDTO contaClienteCreateDTO) throws RegraDeNegocioException {
        ContaClienteEntity contaClienteEntity = objectMapper.convertValue(contaClienteCreateDTO, ContaClienteEntity.class);
        contaClienteEntity.setPontosFidelidade(0);
        contaClienteEntity.setStatus(StatusCliente.ATIVO);
        ContaClienteEntity contaCriada = contaClienteRepository.save(contaClienteEntity);
        ContaClienteDTO contaClienteDTO = objectMapper.convertValue(contaCriada, ContaClienteDTO.class);
        return contaClienteDTO;
    }

    public ContaClienteDTO update(Integer id, ContaClienteCreateDTO contaClienteCreateDTO) throws RegraDeNegocioException {
        getById(id);
        ContaClienteEntity entity = objectMapper.convertValue(contaClienteCreateDTO, ContaClienteEntity.class);
        entity.setIdCliente(id);
        ContaClienteEntity update = contaClienteRepository.save(entity);
        ContaClienteDTO dto = objectMapper.convertValue(update, ContaClienteDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ContaClienteDTO cliente = getById(id);
        ContaClienteEntity clienteEntity = objectMapper.convertValue(cliente, ContaClienteEntity.class);
        contaClienteRepository.delete(clienteEntity);
    }


    @Override
    public void cobrarMensalidade(double valor) {
        ContaClienteEntity cliente = new ContaClienteEntity();
        if (cliente.getPontosFidelidade() > 0) {
            cliente.setPontosFidelidade((int) (cliente.getPontosFidelidade() - valor));
        }
    }

//    public void PremioPontoFidelidade(ContaClienteEntity cliente) throws MessagingException, TemplateException, IOException {
//        emailService.enviarEmailComTemplate(cliente);
//        //TODO ZERAR PONTOS DEPOIS DE GANHAR O PREMIO
//    }
}

