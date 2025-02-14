package com.example.demo.azienda;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificatore unico per la Prenotazione

    @ManyToOne
    private Viaggio viaggio; // Relazione con la classe Viaggio

    @Temporal(TemporalType.DATE)
    private Date dataRichiesta;


    @ManyToOne
    private Dipendente dipendente; // Relazione con la classe Dipendente

    private Date dataPrenotazione; // Data della prenotazione

    // Getter per 'id'
    public Long getId() {
        return id;
    }

    // Setter per 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter per 'viaggio'
    public Viaggio getViaggio() {
        return viaggio;
    }

    // Setter per 'viaggio'
    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    // Getter per 'dipendente'
    public Dipendente getDipendente() {
        return dipendente;
    }

    // Setter per 'dipendente'
    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    // Getter per 'dataPrenotazione'
    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    // Setter per 'dataPrenotazione'
    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    // Costruttore vuoto per JPA
    public Prenotazione() {
    }

    // Costruttore completo per l'inizializzazione
    public Prenotazione(Viaggio viaggio, Dipendente dipendente, Date dataPrenotazione) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataPrenotazione = dataPrenotazione;
    }

    // Metodo per visualizzare la prenotazione in formato stringa
    @Override
    public String toString() {
        return "Prenotazione [id=" + id + ", viaggio=" + viaggio + ", dipendente=" + dipendente + ", dataPrenotazione="
                + dataPrenotazione + "]";
    }

    public void setDataRichiesta(Date data) {
    }
}
