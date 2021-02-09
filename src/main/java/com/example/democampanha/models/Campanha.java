package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_campanhas")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude={"nome", "dataInicioVigencia", "dataFimVigencia"})
@ToString
public class Campanha {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private Integer idTimeCoracao;
        private LocalDate dataInicioVigencia;
        private LocalDate dataFimVigencia;

        @ManyToMany
        @JoinColumn(name = "cliente_id")
        private List<Cliente> cliente = new ArrayList<>();

        public Campanha(Long id, String nome, TimeCoracao timeCoracao, LocalDate dataInicioVigencia,
                        LocalDate dataFimVigencia) {
                this.id = id;
                this.nome = nome;
                setTimeCoracao(timeCoracao);
                this.dataInicioVigencia = dataInicioVigencia;
                this.dataFimVigencia = dataFimVigencia;
        }

        public Campanha(String nome, TimeCoracao timeCoracao, LocalDate dataInicioVigencia,
                        LocalDate dataFimVigencia) {
                this.nome = nome;
                setTimeCoracao(timeCoracao);
                this.dataInicioVigencia = dataInicioVigencia;
                this.dataFimVigencia = dataFimVigencia;
        }

        private void setTimeCoracao(TimeCoracao timeCoracao) {
                if (timeCoracao != null) {
                        this.idTimeCoracao = timeCoracao.getCode();
                }
        }
}
