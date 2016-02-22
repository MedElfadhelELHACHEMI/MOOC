package com.esprit.pidev.models.daos.interfaces;

import com.esprit.pidev.models.entities.Matiere;
import com.esprit.pidev.models.entities.SujetForum;
import com.esprit.pidev.models.entities.Utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface ISujetForumDAO {
    public boolean addSujetForum(SujetForum sujetf) throws SQLException;
    
    public boolean deleteSujetForum(int idSujet) throws SQLException;
    
    public boolean updateSujetForum(SujetForum sujetf)throws SQLException;
    
    public SujetForum getSujetForum(int id)throws SQLException;
    
    public ArrayList<SujetForum> displaySujetForum()throws SQLException;
    
    public ArrayList<SujetForum> displayByUtilisateur(Utilisateur utilisateur)throws SQLException;
    
    public ArrayList<SujetForum> displayByMatiere(Matiere m)throws SQLException;
    
    public ArrayList<SujetForum> searchSujetForum(String rch)throws SQLException;
}
