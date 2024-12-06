package com.example.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Livraison;
import com.example.wrappers.LivraisonWrapper;

@Service
public class LivraisonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LivraisonWrapper livraisonWrapper;

    public List<Livraison> getAll() {
        String sql = "SELECT * FROM livraisons;";
        return this.jdbcTemplate.query(sql, this.livraisonWrapper);
    }

    public Livraison getByID(int id) {
        String sql = "SELECT * FROM livraisons WHERE Id= ?";
        return this.jdbcTemplate.queryForObject(sql, this.livraisonWrapper, id);
    }

    public int insert(Livraison livraison) {
        String sql = "INSERT INTO livraisons(FK_livreur, FK_user, FK_colis, date_creation, date_update, date_livraison, status) VALUES (?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(
            sql,
            livraison.getLivreur().getId(),
            livraison.getUser().getId(),
            livraison.getColis().getId(),
            new Date(),
            new Date(),
            livraison.getDate_livraison(),
            livraison.getStatus()
        );
    }

    public int update(Livraison livraison) {
        String sql = "UPDATE livraisons set FK_livreur=?, FK_user=?, FK_colis=?, date_creation=?, date_update=?, date_livraison=?, status=? WHERE Id=?";
        return this.jdbcTemplate.update(
            sql,
            livraison.getLivreur().getId(),
            livraison.getUser().getId(),
            livraison.getColis().getId(),
            new Date(),
            new Date(),
            livraison.getDate_livraison(),
            livraison.getStatus(),
            livraison.getId()
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM livraisons WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }

    public List<Livraison> getByUser(int id) {
        String sql = "SELECT * FROM livraisons WHERE FK_user = ?";
        return this.jdbcTemplate.query(sql, this.livraisonWrapper, id);
    }
}
