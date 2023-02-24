package com.example.demo6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tietokanta {
    static Connection tietoKanta = null;

    static final String url = "jdbc:mariadb://localhost:3306/varausjarjestelma";

    public static Connection TietokannanAvaus() throws SQLException, Exception {
            try {
                // ota yhteys kantaan, kayttaja = root, salasana = root
                tietoKanta = DriverManager.getConnection(url, "root", "viisi5");
            } catch (SQLException e) { // tietokantaan ei saada yhteyttä
                tietoKanta = null;
                throw e;
            } catch (Exception e) { // JDBC ajuria ei löydy
                throw e;
            }
            return tietoKanta; // palautetaan tietokantayhteys
        }

    public static void suljeTietokanta() {
        try {
            tietoKanta.close();
            System.out.println("Tietokanta suljettu");
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }

}
