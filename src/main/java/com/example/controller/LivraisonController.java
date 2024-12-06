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

import com.example.model.Livraison;
import com.example.services.LivraisonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LivraisonController is a REST controller that handles HTTP requests for managing livraisons.
 * It provides endpoints to perform CRUD operations on livraisons.
 * 
 * Endpoints:
 * - GET /livraisons: Retrieve all livraisons.
 * - GET /livraisons/{id}: Retrieve a livraison by its ID.
 * - POST /livraisons: Insert a new livraison.
 * - PATCH /livraisons/{id}: Update an existing livraison by its ID.
 * - DELETE /livraisons/{id}: Delete a livraison by its ID.
 */
@Controller
@RequestMapping("/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Handles HTTP GET requests to retrieve all livraison data.
     *
     * @return ResponseEntity containing a JSON representation of all livraison data
     *         with HTTP status 200 (OK) if successful, or HTTP status 500 (Internal Server Error)
     *         if an error occurs during JSON processing.
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(livraisonService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a Livraison entity by its ID.
     *
     * @param id the ID of the Livraison entity to retrieve
     * @return a ResponseEntity containing the JSON representation of the Livraison entity
     *         and HTTP status 200 (OK) if found, or HTTP status 404 (Not Found) if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(livraisonService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Inserts a new Livraison object.
     *
     * @param livraison the Livraison object to be inserted
     * @return a ResponseEntity containing a JSON message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Livraison livraison){
        try {
            livraisonService.insert(livraison);
            String jsonData = objectMapper.writeValueAsString("Livraison ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Livraison resource.
     *
     * @param livraison the Livraison object containing updated information
     * @param id the ID of the Livraison to be updated
     * @return a ResponseEntity containing a JSON message and HTTP status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Livraison livraison, @PathVariable("id") int id) {
        try {
            Livraison existingLivraison = livraisonService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingLivraison == null) {
                return new ResponseEntity<>("{\"error\": \"Livreur not found\"}", headers , HttpStatus.NOT_FOUND);
            }

            if (livraison.getLivreur() != null) existingLivraison.setLivreur(livraison.getLivreur());
            if (livraison.getUser() != null) existingLivraison.setUser(livraison.getUser());
            if (livraison.getColis() != null) existingLivraison.setColis(livraison.getColis());
            if (livraison.getDate_update() != null) existingLivraison.setDate_update(livraison.getDate_update());
            if (livraison.getDate_livraison() != null) existingLivraison.setDate_livraison(livraison.getDate_livraison());
            if (livraison.getStatus() != null) existingLivraison.setStatus(livraison.getStatus());

            livraisonService.update(existingLivraison);
            String jsonData = objectMapper.writeValueAsString("Livraison Modifié");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a Livraison by its ID.
     *
     * @param id the ID of the Livraison to be deleted
     * @return a ResponseEntity containing a success message and HTTP status OK if the deletion is successful,
     *         or an error message and HTTP status INTERNAL_SERVER_ERROR if the deletion fails
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            livraisonService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Livraison supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getByUserID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(livraisonService.getByUser(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
