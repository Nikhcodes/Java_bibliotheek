package dao;

import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KwitantieBoekDAO {

    public boolean koppelBoekAanKwitantie(int kwitantienummer, String isbn) {
        String sql = "INSERT INTO kwitantie_boek (kwitantienummer, boek_isbn) VALUES (?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kwitantienummer);
            stmt.setString(2, isbn);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
