package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    public ResponseEntity<Dipendente> creaDipendente(@RequestBody Dipendente dipendente) {
        try {
            return ResponseEntity.ok(dipendenteService.creaDipendente(dipendente));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/immagine")
    public ResponseEntity<Void> caricaImmagine(@PathVariable Long id, @RequestParam("file") byte[] file) throws IOException {
        try {
            dipendenteService.caricaImmagine(id, file);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
