package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Suivre;
import com.example.services.CoursService;
import com.example.services.EtudiantService;

@Component
public class SuivreWrapper implements RowMapper<Suivre> {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private CoursService coursService;

    @Override
    public Suivre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Suivre(this.etudiantService.getByID(rs.getInt("FK_Etudiant")), this.coursService.getByID(rs.getInt("FK_Cour")));
    }
    
}
