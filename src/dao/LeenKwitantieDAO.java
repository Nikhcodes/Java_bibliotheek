package dao;

import model.LeenKwitantie;
import util.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;

public class LeenKwitantieDAO {

    public int maakNieuweKwitantie(int lidnummer, int duur_dagen) {
        String sql = "INSERT INTO leenkwitantie (lidnummer, leendatum, duur_dagen) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, lidnummer);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setInt(3, duur_dagen);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean registreerInlevering(int kwitantienummer, LocalDate inleverdatum, String opmerking) {
        String sql = "UPDATE leenkwitantie SET inleverdatum = ?, opmerking = ? WHERE kwitantienummer = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(inleverdatum));
            stmt.setString(2, opmerking);
            stmt.setInt(3, kwitantienummer);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registreerInleveringVoorBoek(int lidnummer, String isbn, LocalDate inleverdatum, String opmerking) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "UPDATE leenkwitantie lk " +
                    "JOIN kwitantie_boek kb ON lk.kwitantienummer = kb.kwitantienummer " +
                    "SET lk.inleverdatum = ?, lk.opmerking = ? " +
                    "WHERE lk.lidnummer = ? AND kb.isbn = ? AND lk.inleverdatum IS NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(inleverdatum));
            stmt.setString(2, opmerking);
            stmt.setInt(3, lidnummer);
            stmt.setString(4, isbn);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
