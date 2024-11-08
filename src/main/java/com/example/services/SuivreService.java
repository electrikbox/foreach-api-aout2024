package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.Cours;
import com.example.model.Etudiant;
import com.example.model.Suivre;
import com.example.wrappers.SuivreWrapper;

public class SuivreService extends DatabaseService{

    public List<Suivre> getAll(){
        String sql = "SELECT * FROM Suivre;";
        return super.getJdbcTemplate().query(sql, new SuivreWrapper());
    }

    public List<Suivre> getByEtudiantID(int id){
        String sql = "SELECT * FROM Suivre WHERE FK_Etudiant=?";
        return super.getJdbcTemplate().query(sql, new SuivreWrapper(), id);
    }
    
    public List<Suivre> getByCourID(int id){
        String sql = "SELECT * FROM Suivre WHERE FK_Cour=?";
        return super.getJdbcTemplate().query(sql, new SuivreWrapper(), id);
    }

    public int deleteAllByEtudiantID(int id){
        String sql = "DELETE FROM Suivre WHERE FK_Etudiant = ?";
        return super.getJdbcTemplate().update(sql, id);
    }

    public int deleteAllByCourID(int id){
        String sql = "DELETE FROM Suivre WHERE FK_Cour = ?";
        return super.getJdbcTemplate().update(sql, id);
    }

    public int deleteAllByCourID(Cours cour){
        String sql = "DELETE FROM Suivre WHERE FK_Cour = ?";
        return super.getJdbcTemplate().update(sql, cour.getId());
    }

    public int delete(int idCour, int idEtudiant){
        String sql = "DELETE FROM Suivre WHERE FK_Cour = ? AND FK_Etudiant = ?";
        return super.getJdbcTemplate().update(sql, idCour, idEtudiant);
    }

    public int insert(int idCour, int idEtudiant){
        String sql ="INSERT INTO Suivre VALUES (?,?)";
        return super.getJdbcTemplate().update(sql, idCour, idEtudiant);
    }

    public int insert(Cours cour, Etudiant etudiant){
        String sql ="INSERT INTO Suivre VALUES (?,?)";
        return super.getJdbcTemplate().update(sql, cour.getId(), etudiant.getId());
    }
}
