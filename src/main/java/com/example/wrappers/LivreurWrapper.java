package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Livreur;

@Component
public class LivreurWrapper implements RowMapper<Livreur> {

    @Override
    public Livreur mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Livreur(
            rs.getInt("id"), 
            rs.getString("nom"), 
            rs.getString("prenom"), 
            rs.getString("telephone"), 
            rs.getString("email")
        );
    }
    
}
