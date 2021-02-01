package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
        private Date dataInicioVigencia;
        private Date dataFimVigencia;

        public Campanha(Long id, String nome, TimeCoracao timeCoracao, Date dataInicioVigencia, Date dataFimVigencia) {
                this.id = id;
                this.nome = nome;
                setTimeCoracao(timeCoracao);
                this.dataInicioVigencia = dataInicioVigencia;
                this.dataFimVigencia = dataFimVigencia;
        }

        public TimeCoracao getTimeCoracao() {
                return TimeCoracao.valueOf(idTimeCoracao);
        }

        private void setTimeCoracao(TimeCoracao timeCoracao) {
                if (timeCoracao != null) {
                        this.idTimeCoracao = timeCoracao.getCode();
                }
        }
}
