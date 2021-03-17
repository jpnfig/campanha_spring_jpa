package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CampanhaRequest {
    @NotBlank
    private String nome;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataInicioVigencia;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataFimVigencia;

    @NotNull
    private TimeCoracao timeCoracao;

}
