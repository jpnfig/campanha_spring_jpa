package com.example.democampanha.repositories;

import com.example.democampanha.models.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
