package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Etudiant;
import com.example.wrappers.EtudiantWrapper;

@Service
public class EtudiantService {
    
  @Autowired
  private JdbcTemplate jdbcTemplate;
   
  @Autowired
  EtudiantWrapper etudiantWrapper;
  
  public List<Etudiant> getAll(){
        String sql = "SELECT * FROM etudiants;";
        return this.jdbcTemplate.query(sql, this.etudiantWrapper);
   } 

   public Etudiant getByID(int id){
        String sql = "SELECT * FROM etudiants WHERE Id= ?";
        return this.jdbcTemplate.queryForObject(sql, this.etudiantWrapper,id);
   }

   public List<Etudiant> getByCourID(int id){
        String sql = "SELECT ET.* FROM etudiants ET INNER JOIN suivre SU ON SU.FK_Etudiant = ET.Id WHERE FK_Cour=?;";
        return this.jdbcTemplate.query(sql, this.etudiantWrapper,id);
   }

   public int insert(Etudiant etudiant){
     String sql = "INSERT INTO etudiants(Nom,Prenom,Email,Telephone) VALUES (?,?,?,?)";
     return this.jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(), etudiant.getTelephone());
   }

   public int update(Etudiant etudiant){
     String sql = "UPDATE etudiants set Nom=?,Prenom=?,Email=?,Telephone=? WHERE Id=?";
     return this.jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(), etudiant.getTelephone(), etudiant.getId());
   }

   public int delete(int id){
     String sql= "DELETE FROM etudiants WHERE Id=?";
     return this.jdbcTemplate.update(sql,id);
   }
   
}
