/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models.daos.interfaces.implementations;

import com.esprit.pidev.models.daos.interfaces.IReclamationDAO;
import com.esprit.pidev.models.database.DataSource;
import com.esprit.pidev.models.entities.Apprenant;
import com.esprit.pidev.models.entities.Reclamation;
import com.esprit.pidev.models.enums.Etat;
import com.esprit.pidev.models.enums.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BoB
 */
public class ImplReclamationDAO implements IReclamationDAO {

    @Override
    public boolean ajouterReclamation(Reclamation reclamation, int idUtilisateur) throws SQLException {

        Connection connection = DataSource.getInstance().getConnection();

        String requete = "insert into reclamation (id_utilisateur,sujet,description,etat,date_reclamation) values (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, idUtilisateur);
        ps.setString(2, reclamation.getSujet());
        ps.setString(3, reclamation.getDescription());
        ps.setString(4, String.valueOf(Etat.ATT));
        ps.setString(5, reclamation.getDateReclamation());

        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;

    }

    @Override
    public List<Reclamation> getReclamationsByIdUser(int idUtilisateur) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        Connection connection = DataSource.getInstance().getConnection();
        String requete = "select * from reclamation where id_utilisateur = ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, idUtilisateur);
        ResultSet rs = ps.executeQuery();
        Reclamation reclamation = new Reclamation();
        while (rs.next()) {
            reclamation.setIdReclamation(rs.getInt(1));
            reclamation.setIdUtilisateur(rs.getInt(2));
            reclamation.setSujet(rs.getString(3));
            reclamation.setDescription(rs.getString(4));
            reclamation.setEtat(Etat.valueOf(rs.getString(5)));
            reclamation.setDateReclamation(rs.getString(6));
            list.add(reclamation);
        }
        ps.close();
        return list;
    }

    @Override
    public boolean modifierReclamation(int idReclamation, Reclamation newReclamation) throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();

        String requete = "update reclamation set sujet=? , description=?,etat=?,date_reclamation=?   where id_reclamation = ?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setString(1, newReclamation.getSujet());
        ps.setString(2, newReclamation.getDescription());
        ps.setString(3, newReclamation.getEtat().name());
        ps.setString(4, newReclamation.getDateReclamation());
        ps.setInt(5, idReclamation);
        int resultat = ps.executeUpdate();
        ps.close();
        return resultat == 1;
    }

}
