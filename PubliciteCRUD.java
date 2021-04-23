/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.Offre;
import entity.Publicite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tools.MyConnection;

/**
 *
 * @author ASUS
 */
public class PubliciteCRUD {
     public ObservableList<Offre> getOffre() {

        ObservableList<Offre> ListeOffre = FXCollections.observableArrayList();
        String requete = "SELECT id,delai,description,titre,taux FROM Offre ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);
               Offre a;

                while (rs.next()) {
 Offre of = new Offre(rs.getInt("id"),rs.getString("delai"), rs.getString("description"), rs.getString("titre"),rs.getString("image"), rs.getInt("taux"));               //   Sujet suj = new Sujet(rs.getString("id_suj"));
           
                    ListeOffre.add(of);
                }

            } catch (SQLException ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ListeOffre;
    }
      public void addPublicite (Publicite Pu) throws SQLException{
    try{
        String requete = "INSERT INTO publicite (id_offre_id,type,support)"
                + "VALUES(?,?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
     
        pst.setString(1,String.valueOf(Pu.getOffre_id()));
        pst.setString(2,Pu.getType());
        pst.setString(3,Pu.getSupport());
        
        pst.executeUpdate();
    System.out.println("Publicité Ajoutée!");

   }
    catch (SQLException ex) {
               Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle("Impossible!");
              a.setHeaderText(null);
              a.setContentText("Vous avez déjà une publicité!!!");
              a.showAndWait();
        }
    }
      public ObservableList<Publicite> getPublicite() {

        ObservableList<Publicite> ListePublicite = FXCollections.observableArrayList();
        String requete = "SELECT id,id_offre_id,type,support FROM publicite ";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //Statement st;
            ResultSet rs;
            try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(requete);
               Publicite p;

                while (rs.next()) {
                  Publicite pu = new Publicite(rs.getInt("id"),rs.getInt("id_offre_id"),rs.getString("type"),rs.getString("support"));
               //   Sujet suj = new Sujet(rs.getString("id_suj"));
           
                    ListePublicite.add(pu);
                }

            } catch (SQLException ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");

            }
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ListePublicite;
    }
}
