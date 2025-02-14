package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Transactional
    public Viaggio creaViaggio(Viaggio viaggio) {
        // Aggiungi logica di validazione, se necessaria
        if (viaggio.getDestinazione() == null || viaggio.getStato() == null || viaggio.getData() == null) {
            throw new IllegalArgumentException("Destinazione, stato e data sono obbligatori");
        }
        return viaggioRepository.save(viaggio);
    }

    @Transactional
    public Viaggio aggiornaViaggio(Long id, Viaggio viaggioAggiornato) {
        Optional<Viaggio> viaggioEsistente = viaggioRepository.findById(id);
        if (!viaggioEsistente.isPresent()) {
            throw new IllegalArgumentException("Viaggio non trovato con id: " + id);
        }

        Viaggio viaggio = viaggioEsistente.get();
        // Aggiorna i campi del viaggio
        viaggio.setDestinazione(viaggioAggiornato.getDestinazione());
        viaggio.setStato(viaggioAggiornato.getStato());
        viaggio.setData(viaggioAggiornato.getData());

        return viaggioRepository.save(viaggio);
    }

    @Transactional
    public void modificaStatoViaggio(Long id, String nuovoStato) {
        Optional<Viaggio> viaggioEsistente = viaggioRepository.findById(id);
        if (!viaggioEsistente.isPresent()) {
            throw new IllegalArgumentException("Viaggio non trovato con id: " + id);
        }

        Viaggio viaggio = viaggioEsistente.get();
        viaggio.setStato(Viaggio.StatoViaggio.valueOf(nuovoStato)); // Modifica lo stato (ad esempio, da "in programma" a "completato")

        viaggioRepository.save(viaggio);
    }

    @Transactional
    public List<Viaggio> listaViaggi() {
        return viaggioRepository.findAll();
    }

    @Transactional
    public Optional<Viaggio> getViaggio(Long id) {
        return viaggioRepository.findById(id);
    }

    @Transactional
    public void assegnaDipendenteAViaggio(Long idViaggio, Long idDipendente) {
        Optional<Viaggio> viaggioOptional = viaggioRepository.findById(idViaggio);
        if (!viaggioOptional.isPresent()) {
            throw new IllegalArgumentException("Viaggio non trovato con id: " + idViaggio);
        }

        Viaggio viaggio = viaggioOptional.get();

        CrudRepository dipendenteRepository = null;
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(idDipendente);
        if (!dipendenteOptional.isPresent()) {
            throw new IllegalArgumentException("Dipendente non trovato con id: " + idDipendente);
        }

        Dipendente dipendente = dipendenteOptional.get();

        // Verifica se il dipendente ha già una prenotazione nello stesso giorno
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendente, viaggio.getData());
        if (!prenotazioni.isEmpty()) {
            throw new IllegalArgumentException("Il dipendente ha già una prenotazione per il giorno " + viaggio.getData());
        }

        // Crea la prenotazione per il dipendente
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        prenotazione.setDataRichiesta(viaggio.getData());
        prenotazioneRepository.save(prenotazione);
    }
}
