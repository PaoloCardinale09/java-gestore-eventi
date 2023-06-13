package org.lessons.java.gestore.eventi;

import org.lessons.java.gestore.eventi.exceptions.InvalidCapacity;
import org.lessons.java.gestore.eventi.exceptions.InvalidDateException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrganizzaConcerto {
    public static void main(String[] args) throws InvalidDateException, InvalidCapacity {
        // aggiungo Scanner
        Scanner scan = new Scanner(System.in);

        //formatter
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("INSERISCI NUOVO CONCERTO");
        System.out.println("Titolo del concerto: ");
        String title = scan.nextLine();
        System.out.println("Data del concerto: (gg/mm/yyy)");
        LocalDate date = LocalDate.parse(scan.nextLine(), formatDate);
        System.out.println("Inserire ora del concerto: (HH:mm ");
        LocalTime time = LocalTime.parse(scan.nextLine(), formatTime);
        System.out.println("Posti totali: ");
        int totalCapacity = Integer.parseInt(scan.nextLine());
        System.out.println("Prezzo del biglietto: ");
        BigDecimal price = new BigDecimal(scan.nextLine());

        // Instanzio il concerto
        Concerto concerto = new Concerto(title, date, totalCapacity, time, price);
        // Creo variabile per sapere quanti posti disponibili ci sono
        int nPrenotazioni = 0;
        int nDismiss = 0;
        int postiDisponibili = totalCapacity - nPrenotazioni;

        // chiedo se e quante prenotazioni si vogliono fare
        System.out.println("Vuoi fare delle prenotazioni? (S/N)");
        String choice = scan.nextLine();
        switch (choice.toUpperCase()) {
            case "S" -> {
                System.out.println("Quante prenotazioni vuoi fare? ");
                int prenotazioni = Integer.parseInt(scan.nextLine());
                nPrenotazioni += prenotazioni;

                try {
                    concerto.prenota(nPrenotazioni);
                    System.out.println("Hai prenotato " + nPrenotazioni + " posti");
                    // Aggiorna il valore di postiDisponibili
                    postiDisponibili = totalCapacity - nPrenotazioni;
                    System.out.println("Posti disponibili " + postiDisponibili);
                } catch (InvalidCapacity e) {
                    System.out.println("ERRORE: posti disponibili " + postiDisponibili);
                }
            }
            case "N" -> {
                System.out.println("Bye Bye");
            }
        }

        System.out.println("Vuoi disdire delle prenotazioni? (S/N)");
        String disChoice = scan.nextLine();
        switch (disChoice.toUpperCase()) {
            case "S" -> {
                System.out.println("Quante prenotazioni vuoi disdire? ");
                int dismiss = Integer.parseInt(scan.nextLine());
                if (dismiss > nPrenotazioni) {
                    System.out.println("Hai prenotato solo " + nPrenotazioni + " posti. Impossibile disdire più di quelli prenotati.");
                } else {
                    nDismiss += dismiss;

                    try {
                        concerto.disdici(nDismiss);
                        System.out.println("Hai disdetto " + dismiss + " posti");
                        // Aggiorna il valore di postiDisponibili
                        postiDisponibili += nDismiss;
                        System.out.println("Posti disponibili: " + postiDisponibili);
                    } catch (InvalidCapacity e) {
                        System.out.println("ERRORE: posti disponibili " + postiDisponibili);
                    }
                }
            }
            case "N" -> {
                System.out.println("Bye Bye");
            }
        }

        System.out.println(concerto);

        scan.close();

    }
}
