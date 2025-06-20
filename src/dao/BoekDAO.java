package dao;

import model.Boek;
import util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoekDAO {

    public List<Boek> getAlleBoeken() {
        List<Boek> boeken = new ArrayList<>();
        String sql = "SELECT * FROM boek";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Boek boek = new Boek(
                        rs.getString("isbn"),
                        rs.getString("titel"),
                        rs.getString("auteur"),
                        rs.getString("editie"),
                        rs.getBoolean("beschikbaar")
                );
                boeken.add(boek);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boeken;
    }

    public Boek getBoekByIsbn(String isbn) {
        String sql = "SELECT * FROM boek WHERE isbn = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Boek(
                        rs.getString("isbn"),
                        rs.getString("titel"),
                        rs.getString("auteur"),
                        rs.getString("editie"),
                        rs.getBoolean("beschikbaar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBeschikbaarheid(String isbn, boolean beschikbaar) {
        String sql = "UPDATE boek SET beschikbaar = ? WHERE isbn = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, beschikbaar);
            stmt.setString(2, isbn);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Extra: Voeg boek toe
    public boolean voegBoekToe(Boek boek) {
        String sql = "INSERT INTO boek (isbn, titel, auteur, editie, beschikbaar) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, boek.getIsbn());
            stmt.setString(2, boek.getTitel());
            stmt.setString(3, boek.getAuteur());
            stmt.setString(4, boek.getEditie());
            stmt.setBoolean(5, boek.isBeschikbaar());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
