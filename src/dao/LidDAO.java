package dao;

import model.Lid;
import util.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LidDAO {

    public Lid getLidById(int bibnummer) {
        String sql = "SELECT * FROM lid WHERE bibnummer = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bibnummer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Lid(
                        rs.getInt("bibnummer"),
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

    public Lid zoekOfVoegToe(String naam, String cbbId, LocalDate geboortedatum) {
        Lid bestaand = getLidByNaam(naam);
        if (bestaand != null) return bestaand;

        String sql = "INSERT INTO lid (naam, cbb_id, geboortedatum) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, naam);
            stmt.setString(2, cbbId);
            stmt.setDate(3, Date.valueOf(geboortedatum));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int bibnummer = rs.getInt(1);
                return new Lid(bibnummer, naam, cbbId, geboortedatum);
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
                        rs.getInt("bibnummer"),
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

    public List<Lid> getAlleLeden() {
        List<Lid> leden = new ArrayList<>();
        String sql = "SELECT * FROM lid";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lid lid = new Lid(
                        rs.getInt("bibnummer"),
                        rs.getString("naam"),
                        rs.getString("cbb_id"),
                        rs.getDate("geboortedatum").toLocalDate()
                );
                leden.add(lid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leden;
    }
}
