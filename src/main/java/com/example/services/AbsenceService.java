package com.example.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Absences;
import com.example.wrappers.AbsenceWrapper;

@Service
public class AbsenceService extends DatabaseService {
    @Autowired
    private AbsenceWrapper absenceWrapper;
    
    public List<Absences> getAll(){
        String sql = "SELECT * FROM absences";
        return super.getJdbcTemplate().query(sql, this.absenceWrapper);
    }

    public Absences getByID(int id){
        String sql = "SELECT * FROM absences WHERE Id = ?";
        return super.getJdbcTemplate().queryForObject(sql, this.absenceWrapper, id);
    }

    public int insert(Absences absences){
        String sql = "INSERT INTO absences(Date_Debut, Date_Fin, Type, FK_Etudiant) VALUES (?,?,?,?)";
        return super.getJdbcTemplate().update(sql,absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId());
    }

    public int insert(Date dateDebut, Date dateFin, String type , int idEtudiant){
        String sql = "INSERT INTO absences(Date_Debut, Date_Fin, Type, FK_Etudiant) VALUES (?,?,?,?)";
        return super.getJdbcTemplate().update(sql,dateDebut, dateFin, type, idEtudiant);
    }

    public int update(Absences absences){
        String sql = "UPDATE absences set Date_Debut = ?, Date_Fin = ?, Type = ?, FK_Etudiant = ? WHERE Id=?";
        return super.getJdbcTemplate().update(sql,absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId(), absences.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM absences WHERE Id=?";
        return super.getJdbcTemplate().update(sql, id);
    }
}
