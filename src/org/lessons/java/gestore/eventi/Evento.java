package org.lessons.java.gestore.eventi;

import org.lessons.java.gestore.eventi.exceptions.InvalidCapacity;
import org.lessons.java.gestore.eventi.exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    // Fields (private)
    private String titolo;
    private LocalDate data;
    private int postiTotale;
    private int postiPrenotati;

    // CONSTUCTORS

    public Evento(String titolo, LocalDate data, int postiTotale) throws InvalidDateException, InvalidCapacity {
        if (data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
        if (postiTotale < 0) {
            throw new InvalidCapacity("ERRORE: Il numero di posti totali non sono sufficienti");
        }
        this.titolo = titolo;
        this.data = data;
        this.postiTotale = postiTotale;
        this.postiPrenotati = 0;
    }

    //GETTERS AND SETTERS


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPostiTotale() {
        return postiTotale;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // METHODS

    //1. prenota : aggiunge uno ai posti prenotati.
    //Se l’evento è già passato o non ha posti disponibili deve sollevare un’eccezione.
    public void prenota() throws InvalidDateException, InvalidCapacity {
        if (data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
        if (postiTotale < 0) {
            throw new InvalidCapacity("ERRORE: Il numero di posti totali non sono sufficienti");
        }
        postiPrenotati++;
    }

    //2. disdici : riduce di uno i posti prenotati.
    //Se l’evento è già passato o non ci sono prenotazioni deve sollevare un’eccezione.
    public void disdici() throws InvalidDateException, InvalidCapacity {
        if (data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
        if (postiPrenotati < 1) {
            throw new InvalidCapacity("ERRORE: Non esiste nessun posto prenotato");
        }
        postiPrenotati--;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

        return "Evento{" +
                "titolo='" + titolo + '\'' +
                ", data=" + data.format(formatter) +
                '}';
    }
}

