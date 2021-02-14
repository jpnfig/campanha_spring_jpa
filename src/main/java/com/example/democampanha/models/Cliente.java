package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;

//  @ManyToOne
    @Enumerated(EnumType.STRING)
    private TimeCoracao timeCoracao;

}
