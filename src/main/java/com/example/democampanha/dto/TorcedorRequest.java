package com.example.democampanha.dto;

import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TorcedorRequest {

    @NotBlank(message = "Campo nome completo não pode estar vazio")
    private String nomeCompleto;

    @NotBlank(message = "Campo email não pode estar vazio")
    private String email;

    @NotBlank(message = "Campo data de nascimento não pode estar vazio")
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private String dataNascimento;

    @NotNull
    private TimeCoracao timeCoracao;

}
