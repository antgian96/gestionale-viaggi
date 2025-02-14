package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<Prenotazione> prenotaViaggio(@RequestBody Prenotazione prenotazione) {
        try {
            return ResponseEntity.ok(prenotazioneService.prenotaViaggio(prenotazione.getViaggio().getId(), prenotazione.getDipendente().getId(), prenotazione));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

