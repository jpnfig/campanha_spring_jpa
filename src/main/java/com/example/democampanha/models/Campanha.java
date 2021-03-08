package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_campanha")
@Data
public class Campanha {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idCampanha;
        private String nome;
        private LocalDate dataInicioVigencia;
        private LocalDate dataFimVigencia;

        @ManyToMany
        @JoinTable(name = "tb_campanha_torcedor",
                joinColumns = @JoinColumn(
                        name = "id_campanha",
                        updatable = false,
                        nullable = false,
                        referencedColumnName = "idCampanha"),
                inverseJoinColumns = @JoinColumn(
                        name = "id_torcedor",
                        updatable = false,
                        nullable = false,
                        referencedColumnName = "idTorcedor"
                )
        )
        private List<Torcedor> torcedores = new ArrayList<>();

//      @ManyToOne
        @Enumerated(EnumType.STRING)
        private TimeCoracao timeCoracao;

}
