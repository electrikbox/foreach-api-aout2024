package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Cours;
import com.example.wrappers.CoursWrapper;

@Service
public class CoursService{
    @Autowired
     private JdbcTemplate jdbcTemplate;
  
    @Autowired
    CoursWrapper coursWrapper;
    
    public List<Cours> getAll(){
        String sql = "SELECT * FROM cours";
        return this.jdbcTemplate.query(sql, coursWrapper);
    }

    public Cours getByID(int id) {
        String sql = "SELECT * FROM cours WHERE Id = ?";
        return this.jdbcTemplate.queryForObject(sql, coursWrapper, id);
    }

    public int insert(Cours cours){
        String sql = "INSERT INTO cours(Date_Debut, Date_Fin, FK_UE, FK_Formateur) VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(sql, cours.getDateDebut(), cours.getDateFin(),cours.getUe().getId(), cours.getFormateur().getId());
    }

    public int update(Cours cours){
        String sql = "UPDATE cours set Date_Debut =?, Date_Fin=?, FK_UE=?, FK_Formateur=? WHERE Id=?";
        return this.jdbcTemplate.update(sql, cours.getDateDebut(), cours.getDateFin(),cours.getUe().getId(), cours.getFormateur().getId(), cours.getId());
    }

    public int delete(int id){
        String sql = "DELETE FROM cours WHERE Id= ?";
        return this.jdbcTemplate.update(sql,id);
    }
}
