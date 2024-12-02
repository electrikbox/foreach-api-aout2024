package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.UE;
import com.example.wrappers.UEWrapper;

@Service
public class UEService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UEWrapper ueWrapper;

    public List<UE> getAll(){
        String sql = "SELECT * FROM ue";
        return this.jdbcTemplate.query(sql, this.ueWrapper);
    }

    public UE getByID(int id){
        String sql = "SELECT * FROM ue WHERE Id = ?";
        return this.jdbcTemplate.queryForObject(sql, this.ueWrapper, id);
    }

    public int insert(UE ue){
        String sql = "INSERT INTO ue(Libelle) VALUES (?)";
        return this.jdbcTemplate.update(sql, ue.getLibelle());
    }

    public int update(UE ue){
        String sql = "UPDATE ue SET Libelle=? WHERE Id=?";
        return this.jdbcTemplate.update(sql, ue.getLibelle(), ue.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM ue WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
