package org.lessons.java.gestore.eventi;

import org.lessons.java.gestore.eventi.exceptions.InvalidCapacity;
import org.lessons.java.gestore.eventi.exceptions.InvalidDateException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    // FIELDS
    private LocalTime ora;
    private BigDecimal prezzo;

    // CONSTRUCTOR
    public Concerto(String titolo, LocalDate data, int postiTotale, LocalTime ora, BigDecimal prezzo) throws InvalidDateException, InvalidCapacity {
        super(titolo, data, postiTotale);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    // GETTERS AND SETTERS
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // METHODS
    public String getDataFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        return getData().format(formatter);
    }

    public String getOraFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(formatter);
    }

    public String getPrezzoFormattato() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00€");
        return decimalFormat.format(prezzo);
    }

    @Override
    public String toString() {
        return "Concerto{" +
                "data= " + getDataFormattata() +
                ", ora= " + getOraFormattata() +
                ", artista= " + getTitolo() +
                ", prezzo= " + getPrezzoFormattato() +
                '}';
    }
}
