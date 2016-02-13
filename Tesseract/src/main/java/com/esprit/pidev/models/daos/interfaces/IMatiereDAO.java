package com.esprit.pidev.models.daos.interfaces;

import com.esprit.pidev.models.entities.Cours;
import com.esprit.pidev.models.entities.Matiere;
import java.sql.SQLException;
import java.util.List;

public interface IMatiereDAO {

    public boolean AjouterMatiere(Matiere m1) throws SQLException;

    public boolean deleteMatiereByName(String nomMatiere) throws SQLException;

    public boolean deleteMatiereById(int idMatiere) throws SQLException;

    public boolean updateMatiere(Matiere m1, int idMatiere) throws SQLException;

    public List<Matiere> findAll() throws SQLException;

    public List<Cours> affichageCoursByMatiere(int idMatiere) throws SQLException;

    public Matiere findMatiereById(int idMatiere) throws SQLException;

    public Matiere findMatiereByName(String nomMatiere) throws SQLException;

}
