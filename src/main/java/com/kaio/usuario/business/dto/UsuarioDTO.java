package com.kaio.usuario.business.dto;


import com.kaio.usuario.infrastructure.entity.Endereco;
import com.kaio.usuario.infrastructure.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {


    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private List<TelefoneDTO>telefones;
    private List<EnderecoDTO>enderecos;
}
