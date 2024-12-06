package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.wrappers.UserWrapper;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserWrapper userWrapper;

    public List<User> getAll() {
        String sql = "SELECT * FROM users;";
        return this.jdbcTemplate.query(sql, this.userWrapper);
    }

    public User getByID(int id) {
        String sql = "SELECT * FROM users WHERE Id= ?";
        return this.jdbcTemplate.queryForObject(sql, this.userWrapper, id);
    }

    public int insert(User user) {
        String sql = "INSERT INTO users(nom, prenom, email) VALUES (?,?,?)";
        return this.jdbcTemplate.update(
            sql,
            user.getNom(),
            user.getPrenom(),
            user.getEmail()
        );
    }

    public int update(User user) {
        String sql = "UPDATE users set nom=?, prenom=?, email=? WHERE Id=?";
        return this.jdbcTemplate.update(
            sql,
            user.getNom(),
            user.getPrenom(),
            user.getEmail(),
            user.getId()
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM users WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
    
}
