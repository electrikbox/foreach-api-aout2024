package com.example.services;

import java.util.Date;
import java.util.List;

import com.example.model.Absences;
import com.example.wrappers.AbsenceWrapper;

public class AbsenceService extends DatabaseService {
  
    public List<Absences> getAll(){
        String sql = "SELECT * FROM absences";
        return super.getJdbcTemplate().query(sql, new AbsenceWrapper());
    }

    public Absences getByID(int id){
        String sql = "SELECT * FROM Absences WHERE id = ?";
        return super.getJdbcTemplate().queryForObject(sql, new AbsenceWrapper());
    }

    public int insert(Absences absences){
        String sql = "INSERT INTO Absences(Date_Debut, Date_Fin, Type, FK_Etudiant) VALUES (?,?,?,?)";
        return super.getJdbcTemplate().update(sql,absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId());
    }

    public int insert(Date dateDebut, Date dateFin, String type , int idEtudiant){
        String sql = "INSERT INTO Absences(Date_Debut, Date_Fin, Type, FK_Etudiant) VALUES (?,?,?,?)";
        return super.getJdbcTemplate().update(sql,dateDebut, dateFin, type, idEtudiant);
    }

    public int update(Absences absences){
        String sql = "UPDATE Absences set Date_Debut = ?, Date_Fin = ?, Type = ?, FK_Etudiant = ? WHERE Id=?";
        return super.getJdbcTemplate().update(sql,absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId(), absences.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM Absences WHERE Id=?";
        return super.getJdbcTemplate().update(sql, id);
    }
}
