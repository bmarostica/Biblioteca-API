package com.dbc.biblioteca.service;

import com.dbc.biblioteca.dto.UsuarioCreateDTO;
import com.dbc.biblioteca.dto.UsuarioDTO;
import com.dbc.biblioteca.entity.UsuarioEntity;
import com.dbc.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> findByLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

//    public UsuarioDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        //TODO VERIFICAR SE VAI SER UTILIZADO OS GRUPOS NO BANCO DE DADOS PARA PODER SETAR NO CRIAR USUARIO
//        UsuarioEntity entity = new UsuarioEntity();
//        entity.setLogin(usuarioCreateDTO.getUsuario());
//        entity.setSenha(new BCryptPasswordEncoder().encode(usuarioCreateDTO.getSenha()));
//        entity.setGrupos(
//                usuarioCreateDTO.getGrupos().stream()
//                        .map(grupoId -> grupoRepository.findById(grupoId)
//                                .orElse(null))
//                        .collect(Collectors.toList())
//        );
//        UsuarioEntity save = usuarioRepository.save(entity);
//        return new UsuarioDTO(save.getIdUsuario(), save.getUsername(), save.getGrupos().stream().map(GrupoEntity::getIdGrupo).collect(Collectors.toList()));
//    }
}
