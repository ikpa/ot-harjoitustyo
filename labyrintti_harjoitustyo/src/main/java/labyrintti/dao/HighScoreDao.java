/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.dao;

import java.sql.*;
import java.io.*;
import java.util.*;
/**
 * Luokka pisteiden säilömiseen ja lukemiseen
 * @author ikpa
 */
public class HighScoreDao {
    private String filename;
    private Connection con;
    private Statement statement;
    
    /**
     * Luo uuden HighSchoolDao-olion. Luokka sidotaan tiettyyn tiedostoon
     * @param filename Tiedoston nimi, jota luetaan ja johon kirjoitetaan
     */
    public HighScoreDao(String filename) {
        this.filename = filename;
        
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + filename);
            statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Pisteet (id INTEGER PRIMARY KEY, nimi TEXT, pisteet INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Kirjoittaa halutun nimen ja pistemäärän tiedostoon csv-formaatissa
     * @param name Pelaajan nimi
     * @param score Pistemäärä
     */
    public void writeScore(String name, int score) {
        try {
            PreparedStatement p = con.prepareStatement("INSERT INTO Pisteet (nimi, pisteet) VALUES (?, ?)");
            p.setString(1, name);
            p.setInt(2, score);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Palauttaa Arrayn, joka sisältää kaikki tiedoston pistetilastot
     * formaatissa "[nimi], [pisteet] pistettä"
     * @return Array, joka sisältää pistetilastot
     */
    public ArrayList<String> neatScores() {
        ArrayList<String> results = new ArrayList<>();
        try {
            ResultSet r = statement.executeQuery("SELECT * FROM Pisteet ORDER BY pisteet DESC, nimi");
            
            while (r.next()) {
                results.add(r.getString("nimi") + ", " + r.getInt("pisteet") 
                        + " pistettä");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return results;
    }
    
    /**
     * Poistaa kaikki tilastot tiedostosta.
     */
    public void clear() {
        try {
            statement.execute("DELETE FROM Pisteet");
            statement.execute("VACUUM");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
