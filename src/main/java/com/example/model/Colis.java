package com.example.model;

public class Colis {

    private int id;
    private int livraisonId;
    private String description;
    private Float poids;
    private String dimensions;
    private Float valeur;

    public Colis(int id, int livraisonId, String description, Float poids, String dimensions, Float valeur) {
        this.id = id;
        this.livraisonId = livraisonId;
        this.description = description;
        this.poids = poids;
        this.dimensions = dimensions;
        this.valeur = valeur;
    }

    public Colis(int livraisonId, String description, Float poids, String dimensions, Float valeur) {
        this.livraisonId = livraisonId;
        this.description = description;
        this.poids = poids;
        this.dimensions = dimensions;
        this.valeur = valeur;
    }

    public Colis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(int livraisonId) {
        this.livraisonId = livraisonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPoids() {
        return poids;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }
}
