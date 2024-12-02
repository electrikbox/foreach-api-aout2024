package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Suivre;
import com.example.services.SuivreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/suivre")
public class SuivreController {
    @Autowired
    private SuivreService suivreService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    //exemple /
    //Utilisateur va devoir aller sur /suivre/
    @GetMapping
    public ResponseEntity<String> getAll(){
      try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // GET
    // exemple /{id}/suivre
    //Utilisateur va devoir aller sur /suivre/1/etudiant
    @GetMapping("/{id}/etudiant")
    public ResponseEntity<String> getBySuivreID(@PathVariable("id") int id){
        try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getByEtudiantID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // exemple /{id}/cours
    //Utilisateur va devoir aller sur /suivre/1/cours
    @GetMapping("/{id}/cours")
    public ResponseEntity<String> getByCourID(@PathVariable("id") int id){
        try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getByCourID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST 
    //exemple /
    //Utilisateur va devoir aller sur /suivre/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Suivre suivre){
        try {
            suivreService.insert(suivre);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Suivre ajouté", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }

    // DELETE
    //exemple /{id}/cours
    //Utilisateur va devoir aller sur /suivre/1/cours
    @DeleteMapping("/{id}/cours")
    public ResponseEntity<String> deleteByCourID(@PathVariable("id") int id){
        try {
            suivreService.deleteAllByCourID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Suivre supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }

    // DELETE
    //exemple /{id}/etudiant
    //Utilisateur va devoir aller sur /suivre/1/etudiant
    @DeleteMapping("/{id}/etudiant")
    public ResponseEntity<String> deleteByEtudiantID(@PathVariable("id") int id){
        try {
            suivreService.deleteAllByEtudiantID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Suivre supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }

    
    // DELETE
    //exemple /{idEtudiant}/{idCour}
    //Utilisateur va devoir aller sur /suivre/1/1
    @DeleteMapping("/{idEtudiant}/{idCour}")
    public ResponseEntity<String> delete(@PathVariable("idEtudiant") int idEtudiant,@PathVariable("idCour") int idCour){
        try {
            suivreService.delete(idCour, idEtudiant); 
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("Suivre supprimé", headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: handle exception
        }
    }
}
