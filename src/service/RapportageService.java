package service;

import util.DatabaseConnector;

import java.sql.*;
import java.util.Scanner;

public class RapportageService {

    public void toonUitgeleendeBoekenPerLid() {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT lk.lidnummer, COUNT(*) AS aantal " +
                    "FROM leenkwitantie lk " +
                    "JOIN kwitantie_boek kb ON lk.kwitantienummer = kb.kwitantienummer " +
                    "WHERE lk.inleverdatum IS NULL " +
                    "GROUP BY lk.lidnummer " +
                    "ORDER BY aantal DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int lidnummer = rs.getInt("lidnummer");
                int aantal = rs.getInt("aantal");
                System.out.println("Lid " + lidnummer + " heeft " + aantal + " boek(en) uitgeleend.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void toonAantalNietIngeleverdeBoeken() {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT COUNT(*) AS aantal FROM leenkwitantie WHERE inleverdatum IS NULL";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int aantal = rs.getInt("aantal");
                System.out.println("Aantal niet-ingeleverde boeken: " + aantal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void startRapportageMenu(Scanner scanner) {
        System.out.println("\n=== Rapportages ===");
        System.out.println("1. Aantal uitgeleende boeken per lid");
        System.out.println("2. Aantal boeken dat nog niet is ingeleverd");
        System.out.println("0. Terug");
        System.out.print("Maak een keuze: ");
        String keuze = scanner.nextLine();
        switch (keuze) {
            case "1":
                toonUitgeleendeBoekenPerLid();
                break;
            case "2":
                toonAantalNietIngeleverdeBoeken();
                break;
            case "0":
                break;
            default:
                System.out.println("❗ Ongeldige keuze.");
        }
    }
}
