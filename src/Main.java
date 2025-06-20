import service.UitlenenService;

import java.util.Scanner;

/**
 * BiblioApp Beroepsprodukt Java OOP + Database 1
 * Groepsleden:
 * - Alain Pawirodikromo SE/1124/044
 * - Alicia Mahes BI/1124/015
 * - Joshua Wirjoinangoen SNE/1124/017
 * - Nikhiel Lingard SE/1124/032
 * - Shamar Lobles SE/1124/036
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UitlenenService service = new UitlenenService();

        while (true) {
            System.out.println("\n📚 BiblioApp Menu:");
            System.out.println("1. Boeken uitlenen");
            System.out.println("2. Boeken inleveren");
            System.out.println("3. Afsluiten");
            System.out.print("Maak een keuze: ");

            String keuze = scanner.nextLine();

            switch (keuze) {
                case "1" -> service.leenBoekenUit(scanner);
                case "2" -> service.leverBoekIn(scanner);
                case "3" -> {
                    System.out.println("Tot ziens!");
                    System.exit(3);
                }
                default -> System.out.println("Ongeldige keuze.");
            }
        }
    }
}
