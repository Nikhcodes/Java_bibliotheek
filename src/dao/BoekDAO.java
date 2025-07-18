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
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                boeken.add(new Boek(
                        rs.getString("isbn"),
                        rs.getString("titel"),
                        rs.getString("auteur"),
                        rs.getString("editie"),
                        rs.getBoolean("beschikbaar")
                ));
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

    public void updateBeschikbaarheid(String isbn, boolean beschikbaar) {
        String sql = "UPDATE boek SET beschikbaar = ? WHERE isbn = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, beschikbaar);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
