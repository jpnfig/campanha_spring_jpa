package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_campanhas")
@Data
public class Campanha {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private LocalDate dataInicioVigencia;
        private LocalDate dataFimVigencia;

        @ManyToMany
        @JoinTable(name = "tb_campanha_cliente",
                joinColumns = @JoinColumn(
                        name = "id_cliente",
                        updatable = false,
                        nullable = false,
                        referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "id_campanha",
                        updatable = false,
                        nullable = false,
                        referencedColumnName = "id"
                )
        )
        private List<Cliente> cliente = new ArrayList<>();

//      @ManyToOne
        @Enumerated(EnumType.STRING)
        private TimeCoracao timeCoracao;

}
