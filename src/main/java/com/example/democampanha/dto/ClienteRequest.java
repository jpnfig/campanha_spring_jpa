package com.example.democampanha.dto;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.models.enums.TimeCoracao;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteRequest {

    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;
    private Integer idMeuTimeCoracao;

    public Cliente transformaClienteRequestParaCliente(){
        return new Cliente(nomeCompleto, email, dataNascimento, TimeCoracao.valueOf(idMeuTimeCoracao));
    }
}
