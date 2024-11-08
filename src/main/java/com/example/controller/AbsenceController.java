package com.example.controller;
import com.example.model.Absences;
import com.example.services.AbsenceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// exemple /absences
// Controllers
public class AbsenceController {
    private AbsenceService absencesService;
    private ObjectMapper objectMapper;

    public AbsenceController() {
        this.absencesService = new AbsenceService();
        this.objectMapper = new ObjectMapper();
    }


    // GET
    //exemple /
    //Utilisateur va devoir aller sur /absences/
    public String getAll(){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(absencesService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    public String getByID(int id){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(absencesService.getByID(id));
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /absences/
    public void insert(Absences etudiant){
        absencesService.insert(etudiant);
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    public void update(Absences absences, int id) {
        absences.setId(id);
        absencesService.update(absences);
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    public void delete(int id){
        absencesService.delete(id);
    }
}
