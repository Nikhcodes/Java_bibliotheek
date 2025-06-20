import model.Boek;
import model.Lid;
import service.UitlenenService;
import service.RapportageService;
import dao.BoekDAO;
import dao.LidDAO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UitlenenService uitlenenService = new UitlenenService();
        RapportageService rapportageService = new RapportageService();
        BoekDAO boekDAO = new BoekDAO();
        LidDAO lidDAO = new LidDAO();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("📚 WELKOM BIJ BIBLIOAPP");
            System.out.println("==============================");

            // Toon boeken overzicht
            System.out.println("--- Boeken overzicht ---");
            for (Boek boek : boekDAO.getAlleBoeken()) {
                System.out.printf("%s | %s | %s\n", boek.getIsbn(), boek.getTitel(), boek.isBeschikbaar() ? "✅ beschikbaar" : "❌ uitgeleend");
            }

            // Toon leden overzicht
            System.out.println("\n--- Leden overzicht ---");
            for (Lid lid : lidDAO.getAlleLeden()) {
                System.out.printf("%d | %s\n", lid.getBibnummer(), lid.getNaam());
            }

            System.out.println("\n=== MENU ===");
            System.out.println("1. 📖 Boeken uitlenen");
            System.out.println("2. 📥 Boeken inleveren");
            System.out.println("3. 📊 Rapportages bekijken");
            System.out.println("0. ❌ Afsluiten");
            System.out.print("Maak een keuze: ");

            String keuze = scanner.nextLine();
            switch (keuze) {
                case "1":
                    uitlenenService.leenBoekenUit(scanner);
                    break;
                case "2":
                    uitlenenService.leverBoekIn(scanner);
                    break;
                case "3":
                    rapportageService.startRapportageMenu(scanner);
                    break;
                case "0":
                    System.out.println("Bedankt voor het gebruiken van BiblioApp. Tot ziens!");
                    return;
                default:
                    System.out.println("❗ Ongeldige keuze, probeer opnieuw.");
            }
        }
    }
}
