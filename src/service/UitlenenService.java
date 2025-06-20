package service;

import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UitlenenService {

    private final LidDAO lidDAO = new LidDAO();
    private final BoekDAO boekDAO = new BoekDAO();
    private final LeenKwitantieDAO kwitantieDAO = new LeenKwitantieDAO();
    private final KwitantieBoekDAO koppelingDAO = new KwitantieBoekDAO();

    public void leenBoekenUit(Scanner scanner) {
        System.out.print("Voer lidnummer of naam in: ");
        String input = scanner.nextLine().trim();

        Lid lid = input.matches("\\d+") ? lidDAO.getLidById(Integer.parseInt(input))
                : lidDAO.getLidByNaam(input);

        if (lid == null) {
            System.out.println("Lid niet gevonden. Nieuw lid wordt aangemaakt.");
            System.out.print("Naam: ");
            String naam = scanner.nextLine();
            System.out.print("CBB-ID: ");
            String cbbId = scanner.nextLine();
            System.out.print("Geboortedatum (YYYY-MM-DD): ");
            LocalDate geboortedatum = LocalDate.parse(scanner.nextLine());
            lid = lidDAO.voegNieuwLidToe(new Lid(0, naam, cbbId, geboortedatum));
            System.out.println("Nieuw lid toegevoegd met Bibnummer: " + lid.getBibnummer());
        }

        System.out.print("Leenduur in dagen: ");
        int duur = Integer.parseInt(scanner.nextLine());

        int kwitantienummer = kwitantieDAO.maakNieuweKwitantie(lid.getBibnummer(), duur);
        if (kwitantienummer == -1) {
            System.out.println("Fout bij aanmaken kwitantie.");
            return;
        }

        while (true) {
            toonBoeken();
            System.out.print("Voer ISBN in van het boek om uit te lenen (of leeg om te stoppen): ");
            String isbn = scanner.nextLine().trim();
            if (isbn.isEmpty()) break;

            Boek boek = boekDAO.getBoekByIsbn(isbn);
            if (boek == null) {
                System.out.println("❗ Boek niet gevonden.");
                continue;
            }

            if (!boek.isBeschikbaar()) {
                System.out.println("❗ Boek is al uitgeleend.");
                continue;
            }

            if (koppelingDAO.koppelBoekAanKwitantie(kwitantienummer, isbn)) {
                boekDAO.updateBeschikbaarheid(isbn, false);
                System.out.println("✅ Boek uitgeleend: " + boek.getTitel());
            }
        }
    }

    public void leverBoekIn(Scanner scanner) {
        System.out.print("Voer lidnummer in: ");
        int lidnummer = Integer.parseInt(scanner.nextLine());

        System.out.print("Voer ISBN van boek in: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Inleverdatum (YYYY-MM-DD): ");
        LocalDate inleverdatum = LocalDate.parse(scanner.nextLine());

        System.out.print("Opmerkingen (optioneel): ");
        String opmerkingen = scanner.nextLine();

        // Voorbeeld: direct laatste kwitantie zoeken is optioneel uit te breiden
        if (kwitantieDAO.registreerInleveringVoorBoek(lidnummer, isbn, inleverdatum, opmerkingen)) {
            boekDAO.updateBeschikbaarheid(isbn, true);
            System.out.println("✅ Boek succesvol ingeleverd.");
        } else {
            System.out.println("❗ Inleveren mislukt.");
        }
    }

    private void toonBoeken() {
        List<Boek> boeken = boekDAO.getAlleBoeken();
        System.out.println("\n--- Boeken overzicht ---");
        for (Boek b : boeken) {
            System.out.printf("%s | %s | %s%n", b.getIsbn(), b.getTitel(),
                    b.isBeschikbaar() ? "✅ beschikbaar" : "❌ uitgeleend");
        }
    }
}
