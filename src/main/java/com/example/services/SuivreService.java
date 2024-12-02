package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Cours;
import com.example.model.Etudiant;
import com.example.model.Suivre;
import com.example.wrappers.SuivreWrapper;

@Service
public class SuivreService extends DatabaseService{

    @Autowired
    private SuivreWrapper suivreWrapper;

    public List<Suivre> getAll(){
        String sql = "SELECT * FROM suivre;";
        return super.getJdbcTemplate().query(sql, this.suivreWrapper);
    }

    public List<Suivre> getByEtudiantID(int id){
        String sql = "SELECT * FROM suivre WHERE FK_Etudiant=?";
        return super.getJdbcTemplate().query(sql, this.suivreWrapper, id);
    }
    
    public List<Suivre> getByCourID(int id){
        String sql = "SELECT * FROM suivre WHERE FK_Cour=?";
        return super.getJdbcTemplate().query(sql, this.suivreWrapper, id);
    }

    public int deleteAllByEtudiantID(int id){
        String sql = "DELETE FROM suivre WHERE FK_Etudiant = ?";
        return super.getJdbcTemplate().update(sql, id);
    }

    public int deleteAllByCourID(int id){
        String sql = "DELETE FROM suivre WHERE FK_Cour = ?";
        return super.getJdbcTemplate().update(sql, id);
    }

    public int deleteAllByCourID(Cours cour){
        String sql = "DELETE FROM suivre WHERE FK_Cour = ?";
        return super.getJdbcTemplate().update(sql, cour.getId());
    }

    public int delete(int idCour, int idEtudiant){
        String sql = "DELETE FROM suivre WHERE FK_Cour = ? AND FK_Etudiant = ?";
        return super.getJdbcTemplate().update(sql, idCour, idEtudiant);
    }

    public int insert(int idCour, int idEtudiant){
        String sql ="INSERT INTO suivre VALUES (?,?)";
        return super.getJdbcTemplate().update(sql, idCour, idEtudiant);
    }

    public int insert(Cours cour, Etudiant etudiant){
        String sql ="INSERT INTO suivre VALUES (?,?)";
        return super.getJdbcTemplate().update(sql, cour.getId(), etudiant.getId());
    }

    public int insert(Suivre suivre){
        String sql ="INSERT INTO suivre(FK_Cour, FK_Etudiant) VALUES (?,?)";
        return super.getJdbcTemplate().update(sql, suivre.getCours().getId(), suivre.getEtudiants().getId());
    }
}
