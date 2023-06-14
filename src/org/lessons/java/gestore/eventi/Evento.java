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
        // Controllo se la data è nulla o è precedente ad quella attuale
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
        // Controllo se i posti non è positvo
        if (postiTotale <= 0) {
            throw new InvalidCapacity("ERRORE: Il numero di posti totali deve essere positivo");
        }
        // Controllo se il titolo non è null o vuoto
        if (titolo == null || titolo.isEmpty()) {
            throw new IllegalArgumentException("ERRORE: Il titolo non può essere vuoto");
        }
        validateTitoloNonVuoto(titolo);
        this.titolo = titolo;
        this.data = data;
        this.postiTotale = postiTotale;
        this.postiPrenotati = 0;
    }

    //GETTERS AND SETTERS


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) throws IllegalArgumentException {
        validateTitoloNonVuoto(titolo);
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws InvalidDateException {
        validateDateNonPassato(data);
        this.data = data;
    }

    public int getPostiTotale() {
        return postiTotale;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // METHODS
    // controllo se il titolo non è vuoto
    private void validateTitoloNonVuoto(String s) throws IllegalArgumentException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("ERRORE: Il titolo non può essere vuoto");
        }
    }

    //controllo se la data non è precedente a quella attuale
    private void validateDateNonPassato(LocalDate d) throws InvalidDateException {
        if (d == null || d.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
    }

    public int getAvailablePostiDisponibili() {
        return postiTotale - postiPrenotati;
    }


    // aumento le prenotazioni in base al numero passato dall' utente
    public void prenota(int n) throws InvalidDateException, InvalidCapacity {
        if (data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + " non è valida");
        }
        if (postiTotale < n) {
            throw new InvalidCapacity("ERRORE: Max posti prenotabili: " + postiTotale);
        }
        postiPrenotati += n;
    }

    // aumento di 1 le posizioni prenotate
    public void prenotaOnce() throws InvalidDateException, InvalidCapacity {
        if (this.data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: la data è nel passato");
        }
        if (getAvailablePostiDisponibili() == 0) {
            throw new InvalidCapacity("ERRORE: Max posti prenotabili: " + postiTotale);
        }
        postiPrenotati++;
    }

    public void disdiciOnce() throws InvalidDateException, InvalidCapacity {
        if (this.data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: la data è nel passato");
        }

        if (postiPrenotati <= 0) {
            throw new InvalidCapacity("ERRORE: Non esiste nessun posto prenotato");
        }
        postiPrenotati--;
    }


    //2. disdici : riduce di uno i posti prenotati.
    //Se l’evento è già passato o non ci sono prenotazioni deve sollevare un’eccezione.
    public void disdici(int n) throws InvalidDateException, InvalidCapacity {
        if (data.isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERRORE: La data inserita " + data + "non è valida");
        }
        if (postiPrenotati < n) {
            throw new InvalidCapacity("ERRORE: Non esiste nessun posto prenotato");
        }
        postiPrenotati -= n;
    }

    // metodo per formattare data
    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        return this.data.format(formatter);
    }

    @Override
    public String toString() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

        return "Evento{" +
                "titolo='" + titolo + '\'' +
                //  ", data=" + data.format(formatter) +
                ", data=" + getFormattedDate() +
                '}';
    }
}

