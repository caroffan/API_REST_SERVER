package com.dao;

import com.beans.Ville;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
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
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (res != null) {
                    res.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (res != null) {
                    res.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return villes;

    }

    public void enregistrerVille(Ville newVille) {

        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "INSERT INTO ville_france VALUES (?,?,?,?,?,?,?);";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, newVille.getCodeCommune());
            preparedStatement.setString(2, newVille.getNomCommune());
            preparedStatement.setString(3, newVille.getCodePostal());
            preparedStatement.setString(4, newVille.getLibelleAcheminement());
            preparedStatement.setString(5, newVille.getLigne5());
            preparedStatement.setString(6, newVille.getLatitude());
            preparedStatement.setString(7, newVille.getLongitude());
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void supprimerVille(String codeCommune) {
        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "DELETE FROM ville_france WHERE Code_commune_INSEE=?;";
            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, codeCommune);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modifierVille(String codeCommune, Ville newVille) {
        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "UPDATE ville_france SET Code_commune_INSEE=?, Nom_commune=?, Code_postal=?, Libelle_acheminement=?, Ligne_5=?, Latitude=?, Longitude=? WHERE Code_commune_INSEE=?;";

            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, newVille.getCodeCommune());
            preparedStatement.setString(2, newVille.getNomCommune());
            preparedStatement.setString(3, newVille.getCodePostal());
            preparedStatement.setString(4, newVille.getLibelleAcheminement());
            preparedStatement.setString(5, newVille.getLigne5());
            preparedStatement.setString(6, newVille.getLatitude());
            preparedStatement.setString(7, newVille.getLongitude());
            preparedStatement.setString(8, codeCommune);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modifierColonneVille(String codeCommune, String nomColonne, String newValue) {
        PreparedStatement preparedStatement = null;
        loadDatabase();

        try {
            // Exécution de la requête
            String requete = "UPDATE ville_france SET " +nomColonne+"=? WHERE Code_commune_INSEE=?;";

            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, codeCommune);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
