package com.example.democampanha.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude={"rg", "cpf", "nome", "email"})
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rg;
    private Long cpf;
    private String nome;
    private String email;

}
