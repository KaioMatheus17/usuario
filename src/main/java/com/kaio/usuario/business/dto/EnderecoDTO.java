package com.kaio.usuario.business.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private Long id;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
}
