package com.example.democampanha.dto;

import com.example.democampanha.models.Torcedor;
import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CampanhaResponse {

    private Long idCampanha;
    private String nome;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private TimeCoracao timeCoracao;

    private List<Torcedor> torcedores = new ArrayList<>();

}
