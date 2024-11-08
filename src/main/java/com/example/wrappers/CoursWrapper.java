package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Cours;
import com.example.services.EtudiantService;
import com.example.services.FormateurService;
import com.example.services.UEService;

public class CoursWrapper implements RowMapper<Cours> {
    private UEService ueService;
    private FormateurService formateurService;
    private EtudiantService etudiantService;

    public CoursWrapper() {
        this.ueService = new UEService();
        this.formateurService = new FormateurService();
        this.etudiantService = new EtudiantService();
    }

    

    @Override
    public Cours mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cours(rs.getInt("Id"),
        rs.getDate("Date_Debut"),
        rs.getDate("Date_Fin"),
        this.ueService.getByID(rs.getInt("FK_UE")),
        this.formateurService.getByID(rs.getInt("FK_Formateur")),
        this.etudiantService.getByCourID(rs.getInt("Id"))
        );
    }
    
}
