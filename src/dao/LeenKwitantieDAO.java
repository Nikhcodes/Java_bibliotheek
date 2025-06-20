package dao;

import model.LeenKwitantie;
import util.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeenKwitantieDAO {

    public int maakNieuweKwitantie(int lidBibnummer, int duur) {
        String sql = "INSERT INTO leenkwitantie (leendatum, duur, lid_bibnummer) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, duur);
            stmt.setInt(3, lidBibnummer);
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

    public boolean registreerInlevering(int kwitantienummer, LocalDate inleverdatum, String opmerkingen) {
        String sql = "UPDATE leenkwitantie SET inleverdatum = ?, opmerkingen = ? WHERE kwitantienummer = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(inleverdatum));
            stmt.setString(2, opmerkingen);
            stmt.setInt(3, kwitantienummer);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public LeenKwitantie getLaatsteKwitantieVoorLid(int lidBibnummer) {
        String sql = "SELECT * FROM leenkwitantie WHERE lid_bibnummer = ? ORDER BY kwitantienummer DESC LIMIT 1";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lidBibnummer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LeenKwitantie(
                        rs.getInt("kwitantienummer"),
                        rs.getDate("leendatum").toLocalDate(),
                        rs.getInt("duur"),
                        rs.getDate("inleverdatum") != null ? rs.getDate("inleverdatum").toLocalDate() : null,
                        rs.getString("opmerkingen"),
                        rs.getInt("lid_bibnummer")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<LeenKwitantie> getAlleKwitanties() {
        List<LeenKwitantie> lijst = new ArrayList<>();
        String sql = "SELECT * FROM leenkwitantie";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LeenKwitantie k = new LeenKwitantie(
                        rs.getInt("kwitantienummer"),
                        rs.getDate("leendatum").toLocalDate(),
                        rs.getInt("duur"),
                        rs.getDate("inleverdatum") != null ? rs.getDate("inleverdatum").toLocalDate() : null,
                        rs.getString("opmerkingen"),
                        rs.getInt("lid_bibnummer")
                );
                lijst.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lijst;
    }
}
