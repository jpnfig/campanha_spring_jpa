package com.example.democampanha.config;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.enums.TimeCoracao;
import com.example.democampanha.repositories.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Campanha camp1 = new Campanha(null, "Campanha do Joao", TimeCoracao.SAO_PAULO,
                sdf.parse("01/02/2021 15:01:05"),
                sdf.parse("28/02/2021 15:01:05"));

        campanhaRepository.saveAll(Arrays.asList(camp1));
    }
}
