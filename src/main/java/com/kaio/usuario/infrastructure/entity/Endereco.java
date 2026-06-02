package com.kaio.usuario.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cep")
    private String cep;
    @Column(name ="rua")
    private String rua;
    @Column(name = "numero")
    private String numero;
    @Column(name = "bairro")
    private String bairro;
}
