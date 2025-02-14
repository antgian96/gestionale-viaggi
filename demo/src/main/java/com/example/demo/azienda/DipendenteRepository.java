package com.example.demo.azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByUsername(String username);
}
