package com.kaio.usuario.business.converter;


import com.kaio.usuario.business.dto.EnderecoDTO;
import com.kaio.usuario.business.dto.TelefoneDTO;
import com.kaio.usuario.business.dto.UsuarioDTO;
import com.kaio.usuario.infrastructure.entity.Endereco;
import com.kaio.usuario.infrastructure.entity.Telefone;
import com.kaio.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {


    // convertendo o DTO para Entity
    //retorno                 // parametro recebendo
    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()

                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone((usuarioDTO.getTelefones())))

                .build();
    }

    //Entity                         //recebendo listaDTO
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .bairro(enderecoDTO.getBairro())
                .build();
    }


    // transformando tudo em um telefone com o ".toList" vira uma lista!
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .telefone(telefoneDTO.getTelefone())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO) {
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))

                .build();

    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTO) {
        return enderecoDTO.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTO) {
        return telefoneDTO.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .telefone(telefone.getTelefone())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
//  pesquisa o nome,  nome é diferente de null se for pega o nome senão continua usando o da entity
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
// aqui ele já pega direto e sem alteração
                .id(entity.getId())
                .cpf(usuarioDTO.getCpf() != null ? usuarioDTO.getCpf() : entity.getCpf())
// pesquisa a email, email é diferente de null se for pega o email senão continua usando o da entity
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
// pesquisa a senha  se for diferente de null pega a senha senão continua usando o da entity
                .senha((usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha()))
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity){
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .cep(enderecoDTO.getCep()!= null ? enderecoDTO.getCep() : enderecoEntity.getCep())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .bairro(enderecoDTO.getBairro() != null ? enderecoDTO.getBairro() : enderecoEntity.getBairro())

                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity){
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .telefone(telefoneDTO.getTelefone() != null ? telefoneDTO.getTelefone() : telefoneEntity.getTelefone())
                .build();
    }

}
