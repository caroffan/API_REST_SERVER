package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Bdd {
    private Connection connexion;

    public void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/france", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> recupererVille(String codePostal) {

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        ArrayList<String> villes = new ArrayList<String>();

        loadDatabase();

        try {


            // Exécution de la requête
            String requete = "SELECT * FROM ville_france WHERE Code_postal=?;";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, codePostal);
            res = preparedStatement.executeQuery();

            // Récupération des données
            while (res.next()) {
                villes.add(res.getString("Nom_commune"));


            }

        }catch (SQLException e) {
        }
        return villes;

    }

    public void enregistrerVille(String nomCommune, String codePostal) {

        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "INSERT INTO ville_france VALUES ('',?,?,'','','','');";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, nomCommune);
            preparedStatement.setString(2, codePostal);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
