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

import com.example.model.Colis;
import com.example.services.ColisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ColisController is a REST controller that handles HTTP requests for managing Colis entities.
 * It provides endpoints to perform CRUD operations on Colis.
 * 
 * Endpoints:
 * - GET /colis: Retrieve all Colis.
 * - GET /colis/{id}: Retrieve a Colis by its ID.
 * - POST /colis: Insert a new Colis.
 * - PATCH /colis/{id}: Update an existing Colis by its ID.
 * - DELETE /colis/{id}: Delete a Colis by its ID.
 */
@Controller
@RequestMapping("/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Handles HTTP GET requests to retrieve all colis data.
     *
     * @return a ResponseEntity containing the JSON representation of all colis data
     *         and HTTP status OK if successful, or HTTP status INTERNAL_SERVER_ERROR
     *         if an error occurs during JSON processing.
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(colisService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve a Colis by its ID.
     *
     * @param id the ID of the Colis to retrieve
     * @return a ResponseEntity containing the JSON representation of the Colis and HTTP status 200 (OK) if found,
     *         or a ResponseEntity with HTTP status 404 (Not Found) if the Colis is not found or if there is an error processing the JSON.
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            Colis colis = colisService.getByID(id);
            if (colis == null) {
                return new ResponseEntity<>("Colis not found", HttpStatus.NOT_FOUND);
            }
            String jsonData = objectMapper.writeValueAsString(colis);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inserts a new Colis object into the system.
     *
     * @param colis the Colis object to be inserted
     * @return a ResponseEntity containing a JSON message indicating success or an error message
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Colis colis){
        try {
            colisService.insert(colis);
            String jsonData = objectMapper.writeValueAsString("Colis ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing Colis with the provided details.
     *
     * @param colis the Colis object containing updated details
     * @param id the ID of the Colis to be updated
     * @return a ResponseEntity containing a success message and HTTP status code,
     *         or an error message and HTTP status code if the Colis is not found or an error occurs
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Colis colis, @PathVariable("id") int id) {
        try {
            Colis existingColis = colisService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingColis == null) {
                return new ResponseEntity<>("{\"error\": \"Colis not found\"}", headers , HttpStatus.NOT_FOUND);
            }

            if (colis.getDescription() != null) existingColis.setDescription(colis.getDescription());
            if (colis.getPoids() != null) existingColis.setPoids(colis.getPoids());
            if (colis.getDimensions() != null) existingColis.setDimensions(colis.getDimensions());
            if (colis.getValeur() != null) existingColis.setValeur(colis.getValeur());

            colisService.update(existingColis);
            String jsonData = objectMapper.writeValueAsString("Colis Modifié");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a colis (package) by its ID.
     *
     * @param id the ID of the colis to be deleted
     * @return a ResponseEntity containing a success message and HTTP status OK if the deletion is successful,
     *         or an error message and HTTP status INTERNAL_SERVER_ERROR if the deletion fails
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try {
            colisService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Colis supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
