/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models.daos.interfaces.implementations;

import com.esprit.pidev.models.daos.interfaces.IEpreuveObjectifDAO;
import com.esprit.pidev.models.database.DataSource;
import com.esprit.pidev.models.entities.EpreuveObjectif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bacem
 */
public class ImplEpreuveObjectifDAO implements IEpreuveObjectifDAO {

    Connection connection;
    PreparedStatement pst;
    ResultSet rS;
    private static final String TYPE = "Objectif";

    public ImplEpreuveObjectifDAO() {
        connection =(DataSource.getInstance()).getConnection();
    }
    

    @Override
    public boolean createEpreuveObjectif(EpreuveObjectif epreuveObjectif) {
        try {
            String request="insert into epreuves(difficulte, code, type) values (?,?)";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveObjectif.getDifficulte());
            pst.setString(2, epreuveObjectif.getCodeEpreuve());
            pst.setString(3, TYPE);
            
            int result = pst.executeUpdate();
            pst.close();
            
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }

    @Override
    public boolean deleteEpreuveObjectif(int id) {
        try {
            String request = "dele from epreuves where id="+id;
            int result= pst.executeUpdate(request);
            pst.close();
            return (result==1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateEpreuveObjectif(EpreuveObjectif epreuveObjectif, int id) {
        try {
            String request="update epreuves set difficulte=?, code=? where id=?";
            pst = connection.prepareStatement(request);
            pst.setString(1, epreuveObjectif.getDifficulte());
            pst.setString(2, epreuveObjectif.getCodeEpreuve());
            pst.setInt(4, id);
            int result = pst.executeUpdate();
            pst.close();
            return result==1;
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EpreuveObjectif searchEpreuveObjectif(int id) {
        EpreuveObjectif epreuveObjectif = new EpreuveObjectif();
        try {
            String request="select * fromepreuves where id=?";
            rS = pst.executeQuery(request);
            rS.next();
            epreuveObjectif.setId(rS.getInt("id"));
            epreuveObjectif.setCodeEpreuve(rS.getString("code"));
            epreuveObjectif.setDifficulte(rS.getString("difficulte"));
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuveObjectif;
    }

    @Override
    public List<EpreuveObjectif> displayEpreuveObjectif() {
        List<EpreuveObjectif> epreuves=new ArrayList<>();
        try {
            String request="select * from epreuves";
            pst=connection.prepareStatement(request);
            rS=pst.executeQuery();
            while(rS.next()){
                EpreuveObjectif e=new EpreuveObjectif(rS.getInt(1),rS.getString("code"),rS.getString("difficulte"));
                epreuves.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplEpreuveObjectifDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return epreuves;
    }
    
}