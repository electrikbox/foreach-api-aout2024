package com.example.controller;

import com.example.model.UE;
import com.example.services.UEService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// exemple /ue
// Controllers
public class UEController {
    private UEService ueService;
    private ObjectMapper objectMapper;

    public UEController() {
        this.ueService = new UEService();
        this.objectMapper = new ObjectMapper();
    }

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /ue/
    public String getAll(){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(ueService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    public String getByID(int id){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(ueService.getByID(id));
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /ue/
    public void insert(UE etudiant){
        ueService.insert(etudiant);
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    public void update(UE ue, int id) {
        ue.setId(id);
        ueService.update(ue);
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    public void delete(int id){
        ueService.delete(id);
    }
}
