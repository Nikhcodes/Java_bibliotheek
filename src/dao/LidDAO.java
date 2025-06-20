package dao;

import model.Lid;
import util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LidDAO {

    public List<Lid> getAlleLeden() {
        List<Lid> leden = new ArrayList<>();
        String sql = "SELECT * FROM lid";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                leden.add(new Lid(
                        rs.getInt("lidnummer"),
                        rs.getString("naam"),
                        rs.getString("cbb_id"),
                        rs.getDate("geboortedatum").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leden;
    }

    public Lid getLidById(int lidnummer) {
        String sql = "SELECT * FROM lid WHERE lidnummer = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, lidnummer);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lid(
                        rs.getInt("lidnummer"),
                        rs.getString("naam"),
                        rs.getString("cbb_id"),
                        rs.getDate("geboortedatum").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lid getLidByNaam(String naam) {
        String sql = "SELECT * FROM lid WHERE naam = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, naam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lid(
                        rs.getInt("lidnummer"),
                        rs.getString("naam"),
                        rs.getString("cbb_id"),
                        rs.getDate("geboortedatum").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lid voegNieuwLidToe(Lid lid) {
        String sql = "INSERT INTO lid (naam, cbb_id, geboortedatum) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, lid.getNaam());
            stmt.setString(2, lid.getCbbId());
            stmt.setDate(3, Date.valueOf(lid.getGeboortedatum()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                lid.setBibnummer(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lid;
    }

}
