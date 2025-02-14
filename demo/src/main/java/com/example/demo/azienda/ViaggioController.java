package com.example.demo.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping
    public Viaggio creaViaggio(@RequestBody Viaggio viaggio) {
        return viaggioService.creaViaggio(viaggio);
    }

    @GetMapping
    public List<Viaggio> listaViaggi() {
        return viaggioService.listaViaggi();
    }

    @PutMapping("/{id}")
    public Viaggio aggiornaViaggio(@PathVariable Long id, @RequestBody Viaggio viaggio) {
        return viaggioService.aggiornaViaggio(id, viaggio);
    }
}
