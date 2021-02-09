package com.example.democampanha.dto;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ClienteResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;
    private Integer idMeuTimeCoracao;

    public static ClienteResponse transformaClienteEmClienteResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getIdMeuTimeCoracao());
    }

}
