package org.lessons.java.gestore.eventi;

import org.lessons.java.gestore.eventi.exceptions.InvalidCapacity;
import org.lessons.java.gestore.eventi.exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidDateException, InvalidCapacity {
        // aggiungo Scanner
        Scanner scan = new Scanner(System.in);

        //formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

        System.out.println("INSERISCI NUOVO EVENTO");
        System.out.println("Titolo dell'evento: ");
        String title = scan.nextLine();

        // controllo se la data è valida, e rimango in loop finché non lo è
        // e intercetto le eccezioni con i try catch
        LocalDate date = null;
        do {
            System.out.println("Data dell'evento: (yyyy-MM-dd)");
            String dateString = scan.nextLine();
            try {
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                System.out.println("ERRORE: formato della data non valido");
            }
        } while (date == null);

        
        System.out.println("Posti totali: ");
        int totalCapacity = Integer.parseInt(scan.nextLine());

        // Instanzio l'evento
        Evento evento = new Evento(title, date, totalCapacity);
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

                    for (int i = 0; i < prenotazioni; i++) {
                        evento.prenotaOnce();
                        // Aggiorna il valore di postiDisponibili
                        postiDisponibili = totalCapacity - nPrenotazioni;
                    }
                    System.out.println("Hai prenotato " + nPrenotazioni + " posti");
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
                        for (int i = 0; i < dismiss; i++) {
                            evento.disdiciOnce();
                            // Aggiorna il valore di postiDisponibili
                        }
                        postiDisponibili = postiDisponibili + dismiss;
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
        System.out.println(evento);

        scan.close();

    }
}
