package com.dao;

import beans.Ville;

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

    public List<Ville> recupererVille(String codePostal) {

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        ArrayList<Ville> villes = new ArrayList<>();

        loadDatabase();

        try {


            // Exécution de la requête
            String requete = "SELECT * FROM ville_france WHERE Code_postal=?;";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, codePostal);
            res = preparedStatement.executeQuery();

            // Récupération des données
            while (res.next()) {
                Ville ville = new Ville();
                ville.setCodeCommune(res.getString("Code_commune_INSEE"));
                ville.setNomCommune(res.getString("Nom_commune"));
                ville.setCodePostal(res.getString("Code_postal"));
                ville.setLibelleAcheminement(res.getString("Libelle_acheminement"));
                ville.setLigne5(res.getString("Ligne_5"));
                ville.setLatitude(res.getString("Latitude"));
                ville.setLongitude(res.getString("Longitude"));
                villes.add(ville);
            }

        }catch (SQLException e) {
        }
        return villes;

    }

    public List<Ville> recupererVilles() {

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        ArrayList<Ville> villes = new ArrayList<>();

        loadDatabase();

        try {


            // Exécution de la requête
            String requete = "SELECT * FROM ville_france";
            preparedStatement = connexion.prepareStatement(requete);
            res = preparedStatement.executeQuery();

            // Récupération des données
            while (res.next()) {
                Ville ville = new Ville();
                ville.setCodeCommune(res.getString("Code_commune_INSEE"));
                ville.setNomCommune(res.getString("Nom_commune"));
                ville.setCodePostal(res.getString("Code_postal"));
                ville.setLibelleAcheminement(res.getString("Libelle_acheminement"));
                ville.setLigne5(res.getString("Ligne_5"));
                ville.setLatitude(res.getString("Latitude"));
                ville.setLongitude(res.getString("Longitude"));
                villes.add(ville);
            }

        }catch (SQLException e) {
        }
        return villes;

    }
    public void enregistrerVille(Ville newVille) {

        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "INSERT INTO ville_france VALUES (?,?,?,'','','','');";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, newVille.getCodeCommune());
            preparedStatement.setString(2, newVille.getNomCommune());
            preparedStatement.setString(3, newVille.getCodePostal());
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
