package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Livreur;
import com.example.wrappers.LivreurWrapper;

@Service
public class LivreurService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    LivreurWrapper livreurWrapper;

    public List<Livreur> getAll() {
        String sql = "SELECT * FROM livreurs;";
        return this.jdbcTemplate.query(sql, this.livreurWrapper);
    }

    public Livreur getByID(int id) {
        String sql = "SELECT * FROM livreurs WHERE Id= ?";
        return this.jdbcTemplate.queryForObject(sql, this.livreurWrapper, id);
    }

    public int insert(Livreur livreur) {
        String sql = "INSERT INTO livreurs(nom,prenom,telephone, email) VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(sql, livreur.getNom(), livreur.getPrenom(), livreur.getTelephone(),
                livreur.getEmail());
    }

    public int update(Livreur livreur) {
        String sql = "UPDATE livreurs set nom=?,prenom=?,email=?,telephone=? WHERE Id=?";
        return this.jdbcTemplate.update(sql, livreur.getNom(), livreur.getPrenom(), livreur.getEmail(),
                livreur.getTelephone(), livreur.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM livreurs WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }

}
