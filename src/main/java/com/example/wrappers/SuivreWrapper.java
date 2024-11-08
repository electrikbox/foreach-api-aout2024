package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Suivre;
import com.example.services.CoursService;
import com.example.services.EtudiantService;

public class SuivreWrapper implements RowMapper<Suivre> {
    private EtudiantService etudiantService;
    private CoursService coursService;

    public SuivreWrapper() {
        this.etudiantService = new EtudiantService();
        this.coursService = new CoursService();
    }



    @Override
    public Suivre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Suivre(this.etudiantService.getByID(rs.getInt("FK_Etudiant")), this.coursService.getByID(rs.getInt("FK_Cour")));
    }
    
}
