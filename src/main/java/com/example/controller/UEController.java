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
@Controller
@RequestMapping("/ue")
public class UEController {
    @Autowired
    private UEService ueService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /ue/
    @GetMapping
    public ResponseEntity<String> getAll(){
        try {
            String jsonData = objectMapper.writeValueAsString(ueService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id){
        try {
            String jsonData = objectMapper.writeValueAsString(ueService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /ue/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody UE ue){
        try {
            ueService.insert(ue);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("UE ajouté", headers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UE ue, @PathVariable("id") int id) {
        try {
            UE existingUE = ueService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            if(existingUE == null){
                return new ResponseEntity<>("UE non trouvé", headers, HttpStatus.NOT_FOUND);
            }

            if(ue.getLibelle() != null){
                existingUE.setLibelle(ue.getLibelle());
            }

            ueService.update(existingUE);
            return new ResponseEntity<>("UE modifié", headers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /ue/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            ueService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("UE supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }
}
