package com.example.democampanha.config;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.models.enums.TimeCoracao;
import com.example.democampanha.repositories.CampanhaRepository;
import com.example.democampanha.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Cliente cli1 = new Cliente(null, "Joao Nobrega", "joao@gmail.com",
                LocalDate.of(1990, 03, 15), TimeCoracao.SAO_PAULO);

        clienteRepository.saveAll(Arrays.asList(cli1));

        Campanha camp1 = new Campanha(null, "Campanha do Joao", TimeCoracao.SAO_PAULO,
                LocalDate.of(2021, 02, 01),
                LocalDate.of(2021, 02, 03));

        campanhaRepository.saveAll(Arrays.asList(camp1));
    }
}