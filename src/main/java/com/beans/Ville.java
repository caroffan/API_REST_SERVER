package com.beans;

import org.springframework.stereotype.Component;

public class Ville {
    private String codeCommune;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne5;
    private String latitude;
    private String longitude;


    public Ville() {

    }
    public Ville(String codeCommune, String nomCommune, String codePostal) {
        this.codeCommune = codeCommune;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
    }

    public Ville(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne5, String latitude, String longitude) {
        this.codeCommune = codeCommune;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
        this.libelleAcheminement = libelleAcheminement;
        this.ligne5 = ligne5;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    public String getLigne5() {
        return ligne5;
    }

    public void setLigne5(String ligne5) {
        this.ligne5 = ligne5;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}