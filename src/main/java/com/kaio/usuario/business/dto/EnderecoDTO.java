package com.kaio.usuario.business.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private String cep;
    private String rua;
    private String numero;
    private String bairro;
}
