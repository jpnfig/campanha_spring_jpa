package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CampanhaRequest {
    @NotBlank(message = "Campo nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Campo data de inicio da vigência não pode estar vazio")
    private String dataInicioVigencia;

    @NotBlank(message = "Campo data de fim da vigência não pode estar vazio")
    private String dataFimVigencia;

    @NotNull
    private TimeCoracao timeCoracao;

}
