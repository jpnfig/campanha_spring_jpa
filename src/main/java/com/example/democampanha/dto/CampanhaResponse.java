package com.example.democampanha.dto;

import com.example.democampanha.models.Torcedor;
import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CampanhaResponse {

    private Long idCampanha;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataInicioVigencia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataFimVigencia;
    private TimeCoracao timeCoracao;

    private List<Torcedor> torcedores = new ArrayList<>();

}
