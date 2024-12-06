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

import com.example.model.User;
import com.example.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * UserController is a REST controller that handles HTTP requests for managing users.
 * It provides endpoints for retrieving, creating, updating, and deleting users.
 * 
 * Endpoints:
 * 
 * - GET /users: Retrieves all users.
 * - GET /users/{id}: Retrieves a user by their ID.
 * - POST /users: Creates a new user.
 * - PATCH /users/{id}: Updates an existing user by their ID.
 * - DELETE /users/{id}: Deletes a user by their ID.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Handles the HTTP GET request to retrieve all users.
     *
     * @return a ResponseEntity containing the JSON representation of all users
     *         and HTTP status 200 (OK) if successful, or HTTP status 500 (Internal Server Error)
     *         if there is an error processing the JSON.
     */
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(userService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user data in JSON format and HTTP status 200 (OK) if found,
     *         or a ResponseEntity with a "Not Found" message and HTTP status 404 (Not Found) if the user is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(userService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Inserts a new user into the system.
     *
     * @param user the user to be inserted
     * @return a ResponseEntity containing a JSON message indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody User user) {
        try {
            userService.insert(user);
            String jsonData = objectMapper.writeValueAsString("User ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing user with the provided details.
     *
     * @param user The user details to update.
     * @param id The ID of the user to update.
     * @return A ResponseEntity containing a JSON message indicating the result of the update operation.
     *         - If the user is not found, returns a 404 Not Found status with an error message.
     *         - If the update is successful, returns a 201 Created status with a success message.
     *         - If there is an error processing the JSON, returns a 500 Internal Server Error status.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody User user, @PathVariable("id") int id) {
        try {
            User existingUser = userService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingUser == null) {
                return new ResponseEntity<>("{\"error\": \"User not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (user.getNom() != null) existingUser.setNom(user.getNom());
            if (user.getPrenom() != null) existingUser.setPrenom(user.getPrenom());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());

            userService.update(existingUser);
            String jsonData = objectMapper.writeValueAsString("User Modifié");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity containing a success message and HTTP status OK if the user is deleted,
     *         or an error message and HTTP status INTERNAL_SERVER_ERROR if the deletion fails
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            userService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"user supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
