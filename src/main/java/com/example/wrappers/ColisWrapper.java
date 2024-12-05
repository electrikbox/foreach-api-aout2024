package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Colis;

@Component
public class ColisWrapper implements RowMapper<Colis> {

    @Override
    public Colis mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Colis(
            rs.getInt("id"),
            rs.getInt("livraisonId"),
            rs.getString("description"),
            rs.getFloat("poids"),
            rs.getString("dimensions"),
            rs.getFloat("valeur")
        );
    }
    
}
