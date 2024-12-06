package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Livraison;
import com.example.services.ColisService;
import com.example.services.LivreurService;
import com.example.services.UserService;

@Component
public class LivraisonWrapper implements RowMapper<Livraison> {

    @Autowired
    private LivreurService livreurService;

    @Autowired
    private UserService userService;

    @Autowired
    private ColisService colisService;

    @SuppressWarnings("null")
    @Override
    public Livraison mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Livraison(
            rs.getInt("id"),
            this.livreurService.getByID(rs.getInt("FK_livreur")),
            this.userService.getByID(rs.getInt("FK_user")),
            this.colisService.getByID(rs.getInt("FK_colis")),
            rs.getDate("date_creation"),
            rs.getDate("date_update"),
            rs.getDate("date_livraison"),
            rs.getString("status")
        );
    }
    
}
