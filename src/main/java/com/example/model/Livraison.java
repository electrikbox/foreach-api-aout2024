package com.example.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Livraison {

    private int id;
    private Livreur livreur;
    private User user;
    private Colis colis;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_creation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_update;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_livraison;
    
    private String status;

    public Livraison(int id, Livreur livreur, User user, Colis colis, Date date_creation, Date date_update, Date date_livraison, String status) {
        this.id = id;
        this.livreur = livreur;
        this.user = user;
        this.colis = colis;
        this.date_creation = date_creation;
        this.date_update = date_update;
        this.date_livraison = date_livraison;
        this.status = status;
    }

    public Livraison(Livreur livreur, User user, Colis colis, Date date_creation, Date date_update, Date date_livraison, String status) {
        this.livreur = livreur;
        this.user = user;
        this.colis = colis;
        this.date_creation = date_creation;
        this.date_update = date_update;
        this.date_livraison = date_livraison;
        this.status = status;
    }

    public Livraison() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }
}
