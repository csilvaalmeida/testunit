package com.clodoaldo.api.cerveja.repository;

import com.clodoaldo.api.cerveja.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CervejaRepository extends JpaRepository<Cerveja, Long>{
    Optional<Cerveja> findByNome(String nome);
}
