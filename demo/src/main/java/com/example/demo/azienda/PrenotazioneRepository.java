package com.example.demo.azienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    // Trova la prenotazione di un dipendente per una data specifica


    Optional<Prenotazione> findByDipendenteAndDataPrenotazione(Dipendente dipendente, Date dataPrenotazione);

    List<Prenotazione> findByDipendenteAndDataRichiesta(Dipendente dipendente, Date data);
}
