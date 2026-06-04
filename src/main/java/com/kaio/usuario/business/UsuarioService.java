package com.kaio.usuario.business;


import com.kaio.usuario.business.converter.UsuarioConverter;
import com.kaio.usuario.business.dto.UsuarioDTO;
import com.kaio.usuario.infrastructure.entity.Usuario;
import com.kaio.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    // PARA SALVAR O USUARIO!
          //retorno                 RECEBEU UM OBJETO DTO
    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO){

        // TRANSFORMOU ELE EM UM USUARIO ENTITY
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        // SALVOU NO BANCO DE DADOS E DEPOIS CONVERSTOU PARA DTO
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }


    }



