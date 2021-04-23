/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.Offre;
import entity.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;


/**
 *
 * @author ASMA
 */
public class OffreCRUD {
       public ObservableList<Produit> getProduit() {

        ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
        String requete = "SELECT id,nom,prix FROM Produit ";
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
               Produit p;

                while (rs.next()) {
                   Produit pro = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getFloat("prix"));
               //   Sujet suj = new Sujet(rs.getString("id_suj"));
           
                    ProduitList.add(pro);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProduitList;
    }
        public void addOffre (Offre Of) throws SQLException{
    try{
        String requete = "INSERT INTO Offre (delai,description,titre,taux)"
                + "VALUES(?,?,?,?)" ;
           PreparedStatement pst =
            new MyConnection().cn.prepareStatement(requete);
     
      pst.setString(1,Of.getDelai());
      pst.setString(2,Of.getDescription());
      pst.setString(3,Of.getTitre());
      pst.setString(4,String.valueOf(Of.getTaux()));

      pst.executeUpdate();
    System.out.println("Offre ajoutée!");

   }
    catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public ObservableList<Offre> getOffre() {

        ObservableList<Offre> OffreList = FXCollections.observableArrayList();
        String requete = "SELECT id,delai,description,titre,image,taux FROM Offre ";
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
                   Offre of = new Offre(rs.getInt("id"),rs.getString("delai"), rs.getString("description"), rs.getString("titre"),rs.getString("image"), rs.getInt("taux"));
               //   Sujet suj = new Sujet(rs.getString("id_suj"));
           
                    OffreList.add(of);
                }

            } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return OffreList;
    }
        public ObservableList<Offre> getAllTrier() {
     
  
         ObservableList<Offre> OffreList = FXCollections.observableArrayList();
        String req = "SELECT * FROM Offre order by taux DESC";
        try {
          PreparedStatement st =
            new MyConnection().cn.prepareStatement(req);    
ResultSet rst = st.executeQuery(req);
            
            while (rst.next()){
                Offre s= new Offre();
                
                  s.setDelai(rst.getString("delai"));
                s.setDescription(rst.getString("description"));
                 s.setTitre(rst.getString("titre"));
                                  s.setImage(rst.getString("image"));

                s.setTaux(rst.getInt("taux"));
              
            
                
               
                OffreList.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return OffreList;
     }

}
