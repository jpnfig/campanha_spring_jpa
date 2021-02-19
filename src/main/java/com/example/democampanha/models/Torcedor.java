package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_torcedor")
@Data
public class Torcedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTorcedor;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;

//  @ManyToOne
    @Enumerated(EnumType.STRING)
    private TimeCoracao timeCoracao;

}
