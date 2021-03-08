package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TorcedorResponse {

    private Long idTorcedor;
    private String nomeCompleto;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataNascimento;
    private TimeCoracao timeCoracao;

}
