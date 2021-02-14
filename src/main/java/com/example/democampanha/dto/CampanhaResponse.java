package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CampanhaResponse {

    private Long id;
    private String nome;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private TimeCoracao timeCoracao;

}
