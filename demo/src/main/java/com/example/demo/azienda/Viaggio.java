package com.example.demo.azienda;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinazione;

    private Date data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public enum StatoViaggio {
        IN_PROGRAMMA,
        COMPLETATO
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDestinazione() { return destinazione; }
    public void setDestinazione(String destinazione) { this.destinazione = destinazione; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public StatoViaggio getStato() { return stato; }
    public void setStato(StatoViaggio stato) { this.stato = stato; }
}
