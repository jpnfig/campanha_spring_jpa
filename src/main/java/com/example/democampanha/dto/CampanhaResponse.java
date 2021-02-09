package com.example.democampanha.dto;

import com.example.democampanha.models.Campanha;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CampanhaResponse {

    private Long id;
    private String nome;
    private Integer idTimeCoracao;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public static CampanhaResponse transformaCampanhaEmCampanhaResponse(Campanha campanha) {
        return new CampanhaResponse(
                campanha.getId(),
                campanha.getNome(),
                campanha.getIdTimeCoracao(),
                campanha.getDataInicioVigencia(),
                campanha.getDataFimVigencia());
    }

}
