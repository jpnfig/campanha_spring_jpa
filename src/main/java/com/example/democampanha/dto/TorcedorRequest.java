package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TorcedorRequest {
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String email;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataNascimento;
    private TimeCoracao timeCoracao;

}
