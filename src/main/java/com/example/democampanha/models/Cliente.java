package com.example.democampanha.models;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude={"nomeCompleto", "email", "dataNascimento", "idMeuTimeCoracao"})
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;
    private Integer idMeuTimeCoracao;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Campanha> campanhas = new ArrayList<>();

    public Cliente(Long id, String nomeCompleto, String email, LocalDate dataNascimento, TimeCoracao meuTimeCoracao) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataNascimento = dataNascimento;
        setMeuTimeCoracao(meuTimeCoracao);;
    }

    public TimeCoracao getMeuTimeCoracao() {
        return TimeCoracao.valueOf(idMeuTimeCoracao);
    }

    private void setMeuTimeCoracao(TimeCoracao meuTimeCoracao) {
        if (meuTimeCoracao != null) {
            this.idMeuTimeCoracao = meuTimeCoracao.getCode();
        }
    }
}
