package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione prenotaViaggio(Long idViaggio, Long idDipendente, Prenotazione prenotazione) {
        // Validazione manuale
        if (prenotazione.getDataPrenotazione() == null) {
            throw new IllegalArgumentException("La data di prenotazione è obbligatoria");
        }

        // Verifica che il dipendente non abbia altre prenotazioni nello stesso giorno
        Optional<Prenotazione> esistente = prenotazioneRepository.findByDipendenteAndDataPrenotazione(prenotazione.getDipendente(), prenotazione.getDataPrenotazione());
        if (esistente.isPresent()) {
            throw new IllegalArgumentException("Il dipendente ha già una prenotazione per questa data");
        }

        return prenotazioneRepository.save(prenotazione);
    }
}
