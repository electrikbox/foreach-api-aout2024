package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Colis;
import com.example.wrappers.ColisWrapper;

@Service
public class ColisService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ColisWrapper colisWrapper;

    public List<Colis> getAll() {
        String sql = "SELECT * FROM colis;";
        return this.jdbcTemplate.query(sql, this.colisWrapper);
    }

    public Colis getByID(int id) {
        String sql = "SELECT * FROM colis WHERE Id= ?";
        return this.jdbcTemplate.queryForObject(sql, this.colisWrapper, id);
    }

    public int insert(Colis colis) {
        String sql = "INSERT INTO colis(description, poids, dimensions, valeur) VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(
                sql,
                colis.getDescription(),
                colis.getPoids(),
                colis.getDimensions(),
                colis.getValeur());
    }

    public int update(Colis colis) {
        String sql = "UPDATE colis set description=?, poids=?, dimensions=?, valeur=? WHERE Id=?";
        return this.jdbcTemplate.update(
                sql,
                colis.getDescription(),
                colis.getPoids(),
                colis.getDimensions(),
                colis.getValeur(),
                colis.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM colis WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
