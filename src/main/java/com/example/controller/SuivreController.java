package com.example.controller;

import com.example.services.SuivreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SuivreController {
    private SuivreService suivreService;
    private ObjectMapper objectMapper;

    public SuivreController() {
        this.suivreService = new SuivreService();
        this.objectMapper = new ObjectMapper();
    }

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /suivre/
    public String getAll(){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(suivreService.getAll());
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }


    // GET
    // exemple /{id}/suivre
    //Utilisateur va devoir aller sur /suivre/1/etudiant
    public String getBySuivreID(int id){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString( suivreService.getByEtudiantID(id));
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }

    // GET
    // exemple /{id}/cours
    //Utilisateur va devoir aller sur /suivre/1/cours
    public String getByCourID(int id){
        String jsonData = "";
        try {
            jsonData = objectMapper.writeValueAsString(suivreService.getByCourID(id));
        } catch (JsonProcessingException ex) {
        }
        return jsonData;
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /suivre/
    public void insert(int idEtudiant, int idCour){
        suivreService.insert(idCour, idEtudiant);
    }

    // DELETE
    //exemple /{id}/cours
    //Utilisateur va devoir aller sur /suivre/1/cours
    public void deleteByCourID(int id){
        suivreService.deleteAllByCourID(id);
    }

    // DELETE
    //exemple /{id}/etudiant
    //Utilisateur va devoir aller sur /suivre/1/etudiant
    public void deleteByEtudiantID(int id){
        suivreService.deleteAllByEtudiantID(id);
    }

    
    // DELETE
    //exemple /{idEtudiant}/{idCour}
    //Utilisateur va devoir aller sur /suivre/1/1
    public void deleteByEtudiantID(int idEtudiant, int idCour){
        suivreService.delete(idEtudiant, idCour);
    }
}
