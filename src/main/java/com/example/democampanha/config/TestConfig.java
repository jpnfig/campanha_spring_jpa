package com.example.democampanha.config;

import com.example.democampanha.repositories.CampanhaRepository;
import com.example.democampanha.repositories.TorcedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private TorcedorRepository TorcedorRepository;

    @Override
    public void run(String... args) throws Exception {

    }

}
