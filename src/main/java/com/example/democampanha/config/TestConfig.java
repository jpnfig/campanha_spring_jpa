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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

    }

}
