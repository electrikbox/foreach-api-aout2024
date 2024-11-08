package com.example.controller;

import com.example.model.Formateur;
import com.example.services.FormateurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// exemple /formateurs
// Controllers
public class FormateurController {
    private FormateurService formateurService;
    private ObjectMapper objectMapper;

    public FormateurController() {
        this.formateurService = new FormateurService();
        this.objectMapper = new ObjectMapper();
    }

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /formateurs/
    public String getAll(){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(formateurService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    public String getByID(int id){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(formateurService.getByID(id));
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /formateurs/
    public void insert(Formateur etudiant){
        formateurService.insert(etudiant);
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    public void update(Formateur formateurs, int id) {
        formateurs.setId(id);
        formateurService.update(formateurs);
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    public void delete(int id){
        formateurService.delete(id);
    }
}
