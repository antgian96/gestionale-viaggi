package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente creaDipendente(Dipendente dipendente) {
        if (dipendente.getUsername() == null || dipendente.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Il nome utente è obbligatorio");
        }
        if (dipendente.getEmail() == null || !dipendente.getEmail().contains("@")) {
            throw new IllegalArgumentException("L'email è obbligatoria e deve essere valida");
        }
        return dipendenteRepository.save(dipendente);
    }

    public void caricaImmagine(Long id, byte[] file) throws IOException {
        if (file == null || file.length == 0) {
            throw new IllegalArgumentException("Il file immagine è obbligatorio");
        }
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
        dipendente.setImmagineProfilo(file);
        dipendenteRepository.save(dipendente);
    }
}
