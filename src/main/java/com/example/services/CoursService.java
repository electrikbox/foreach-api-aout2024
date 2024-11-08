package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.Cours;
import com.example.wrappers.CoursWrapper;

public class CoursService extends DatabaseService{
     
  
    public List<Cours> getAll(){
        String sql = "SELECT * FROM Cours";
        return super.getJdbcTemplate().query(sql, new CoursWrapper());
    }

    public Cours getByID(int id) {
        String sql = "SELECT * FROM Cours WHERE Id = ?";
        return super.getJdbcTemplate().queryForObject(sql, new CoursWrapper(), id);
    }

    public int insert(Cours cours){
        String sql = "INSERT INTO Cours(Date_Debut, Date_Fin, FK_UE, FK_Formateur) VALUES (?,?,?,?)";
        return super.getJdbcTemplate().update(sql, cours.getDateDebut(), cours.getDateFin(),cours.getUe().getId(), cours.getFormateur().getId());
    }

    public int update(Cours cours){
        String sql = "UPDATE Cours set Date_Debut =?, Date_Fin=?, FK_UE=?, FK_Formateur=? WHERE Id=?";
        return super.getJdbcTemplate().update(sql, cours.getDateDebut(), cours.getDateFin(),cours.getUe().getId(), cours.getFormateur().getId(), cours.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM Cours WHERE Id= ?";
        return super.getJdbcTemplate().update(sql,id);
    }
}
