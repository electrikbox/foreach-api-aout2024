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

import com.example.model.Livreur;
import com.example.services.LivreurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LivreurController is a REST controller that handles HTTP requests for managing Livreurs.
 * It provides endpoints to get all Livreurs, get a Livreur by ID, insert a new Livreur,
 * update an existing Livreur, and delete a Livreur by ID.
 * 
 * Endpoints:
 * - GET /livreurs: Retrieves all Livreurs.
 * - GET /livreurs/{id}: Retrieves a Livreur by its ID.
 * - POST /livreurs: Inserts a new Livreur.
 * - PATCH /livreurs/{id}: Updates an existing Livreur by its ID.
 * - DELETE /livreurs/{id}: Deletes a Livreur by its ID.
 */
@Controller
@RequestMapping("/livreurs")
public class LivreurController {

    @Autowired
    private LivreurService livreurService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Handles HTTP GET requests to retrieve all Livreur entities.
     *
     * @return a ResponseEntity containing a JSON representation of all Livreur entities
     *         and an HTTP status code of 200 (OK) if successful, or 500 (Internal Server Error)
     *         if there is a JsonProcessingException.
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(livreurService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a Livreur by its ID.
     *
     * @param id the ID of the Livreur to retrieve
     * @return a ResponseEntity containing the JSON representation of the Livreur and HTTP status 200 (OK) if found,
     *         or a ResponseEntity with a "Not Found" message and HTTP status 404 (Not Found) if the Livreur is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(livreurService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Inserts a new Livreur into the system.
     *
     * @param livreur the Livreur object to be inserted
     * @return a ResponseEntity containing a JSON message indicating the result of the operation
     *         and the appropriate HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Livreur livreur){
        try {
            livreurService.insert(livreur);
            String jsonData = objectMapper.writeValueAsString("Livreur ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Livreur with the provided details.
     *
     * @param livreur The Livreur object containing updated details.
     * @param id The ID of the Livreur to be updated.
     * @return A ResponseEntity containing a JSON message indicating the result of the update operation.
     *         - If the Livreur is not found, returns a 404 Not Found status with an error message.
     *         - If the update is successful, returns a 201 Created status with a success message.
     *         - If there is a JSON processing error, returns a 500 Internal Server Error status.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Livreur livreur, @PathVariable("id") int id) {
        try {
            Livreur existingLivreur = livreurService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingLivreur == null) {
                return new ResponseEntity<>("{\"error\": \"Livreur not found\"}", headers , HttpStatus.NOT_FOUND);
            }

            if (livreur.getNom() != null) existingLivreur.setNom(livreur.getNom());
            if (livreur.getPrenom() != null) existingLivreur.setPrenom(livreur.getPrenom());
            if (livreur.getTelephone() != null) existingLivreur.setTelephone(livreur.getTelephone());
            if (livreur.getEmail() != null) existingLivreur.setEmail(livreur.getEmail());

            livreurService.update(existingLivreur);
            String jsonData = objectMapper.writeValueAsString("Livreur Modifié");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a Livreur by its ID.
     *
     * @param id the ID of the Livreur to be deleted
     * @return a ResponseEntity containing a success message and HTTP status OK if the deletion is successful,
     *         or an error message and HTTP status INTERNAL_SERVER_ERROR if the deletion fails
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            livreurService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Livreur supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
