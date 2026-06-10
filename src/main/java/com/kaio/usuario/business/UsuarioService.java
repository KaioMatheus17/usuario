package com.kaio.usuario.business;


import com.kaio.usuario.business.converter.UsuarioConverter;
import com.kaio.usuario.business.dto.EnderecoDTO;
import com.kaio.usuario.business.dto.TelefoneDTO;
import com.kaio.usuario.business.dto.UsuarioDTO;
import com.kaio.usuario.infrastructure.entity.Endereco;
import com.kaio.usuario.infrastructure.entity.Telefone;
import com.kaio.usuario.infrastructure.entity.Usuario;
import com.kaio.usuario.infrastructure.exceptions.ConflictException;
import com.kaio.usuario.infrastructure.exceptions.ResouceNotFoundException;
import com.kaio.usuario.infrastructure.repository.EnderecoRepository;
import com.kaio.usuario.infrastructure.repository.TelefoneRepository;
import com.kaio.usuario.infrastructure.repository.UsuarioRepository;
import com.kaio.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO){
            existeEmail(usuarioDTO.getEmail());
            usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.save(usuario));

    }


    public void existeEmail(String email){
        try{
            boolean existe = verificarEmailExistente(email);
            if (existe){
                throw new ConflictException("Email1 já cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email2 já cadastrado: " + email);
        }
    }


    public boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }


    public UsuarioDTO buscaUsuarioPorEmail(String email){
        try{
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                    ()-> new ResouceNotFoundException("Email não encontrado: " + email)));
        }catch (ConflictException e){
            throw new ConflictException("Email não localizado: " + email);
        }

    }

    public void deletaUsuario(String email){
        usuarioRepository.deleteByEmail(email);
    }


    //para extrair
    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
//ISSO AQUI É APENAS PARA QUEM USA O TOKEN NA SENHA PARA EXTRAIR
        String email = jwtUtil.extractUsername(token.substring(7));

//criptador de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

//Busca dados do usuario no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResouceNotFoundException("Email não localizado" + email));

// Mesctou os dados recebemos na requisição DTO com o dados da Entity banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){

        Endereco enderecoEntity = enderecoRepository.findById(idEndereco).orElseThrow(
                ()-> new ResouceNotFoundException("ID não localizado: " + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, enderecoEntity);
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO autualizaTelefone (Long idTelefone, TelefoneDTO telefoneDTO){
        Telefone telefoneEntity = telefoneRepository.findById(idTelefone).orElseThrow(
                ()-> new ResouceNotFoundException("ID não localizado: " + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, telefoneEntity );

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));


    }

    }



