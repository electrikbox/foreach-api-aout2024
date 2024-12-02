package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/formateurs")
public class FormateurController {
    @Autowired
    private FormateurService formateurService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /formateurs/
    @GetMapping
    public ResponseEntity<String> getAll(){
        try {
            String jsonData = objectMapper.writeValueAsString(formateurService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id){
        try {
            String jsonData = objectMapper.writeValueAsString(formateurService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /formateurs/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Formateur formateur){
        try {
            formateurService.insert(formateur);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Formateur ajouté", headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Probleme lors de l'insertion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Formateur formateurs,@PathVariable("id") int id) {
        try {
            Formateur existingFormateur = formateurService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingFormateur == null) {
                return new ResponseEntity<>("{\"error\": \"Etudiant not found\"}", headers , HttpStatus.NOT_FOUND);
            }

            if (formateurs.getNom() != null) existingFormateur.setNom(formateurs.getNom());
            if (formateurs.getPrenom() != null) existingFormateur.setPrenom(formateurs.getPrenom());
            if (formateurs.getEmail() != null) existingFormateur.setEmail(formateurs.getEmail());
            if (formateurs.getTelephone() != null) existingFormateur.setTelephone(formateurs.getTelephone());

            formateurService.update(existingFormateur);
            return new ResponseEntity<>("Formateur modifié", headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Probleme lors de la modification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /formateurs/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            formateurService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Formateur supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Probleme lors de la suppresion", HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }
}
