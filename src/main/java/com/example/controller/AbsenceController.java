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
@Controller
@RequestMapping("/absences")
public class AbsenceController {
    @Autowired
    private AbsenceService absencesService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /absences/
    @GetMapping
    public ResponseEntity<String> getAll(){
        try {
            String jsonData = objectMapper.writeValueAsString(absencesService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // GET
    // exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id){
        try {
            String jsonData = objectMapper.writeValueAsString(absencesService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /absences/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Absences absence){
        try {
            absencesService.insert(absence);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Abense ajouté", headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }

    // PATCH/PUT
    //exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Absences absence,@PathVariable("id") int id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            Absences existingAbsence = absencesService.getByID(id);
            if(existingAbsence == null){
                return new ResponseEntity<>("Abense pas trouvé", headers, HttpStatus.OK);
            }

            if (absence.getDateDebut() != null) {
                existingAbsence.setDateDebut(absence.getDateDebut());
            }
            if (absence.getDateFin() != null) {
                existingAbsence.setDateFin(absence.getDateFin());
            }
            if(absence.getType() != null){
                existingAbsence.setType(absence.getType());
            }
            if(absence.getEtudiants() != null){
                existingAbsence.setEtudiants(absence.getEtudiants());
            }

            absencesService.update(existingAbsence);
           
            return new ResponseEntity<>("Abense modifié", headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }

    // DELETE
    //exemple /{id}
    //Utilisateur va devoir aller sur /absences/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            absencesService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Abense supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }
}
