package com.example.democampanha.dto;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CampanhaRequest {

    private String nome;
    private Integer idTimeCoracao;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public Campanha transformaCampanhaRequestParaCampanha(){
        return new Campanha(nome, TimeCoracao.valueOf(idTimeCoracao), dataInicioVigencia, dataFimVigencia);
    }
}
