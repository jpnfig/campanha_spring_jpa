package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;
    private TimeCoracao timeCoracao;

}
