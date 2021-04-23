/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.OffreCRUD;
import Services.PubliciteCRUD;
import entity.Offre;
import entity.Publicite;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListePubliciteController implements Initializable {

   @FXML
    private TableView<Publicite> tab;
        PubliciteCRUD pcr = new PubliciteCRUD();
    @FXML
    private TableColumn<Publicite, Integer> id_tab;
    @FXML
    private TableColumn<Publicite, String> type_tab;
    @FXML
    private TableColumn<Publicite, String> support_tab;
   
     int ID;
    private TextField id_offre;
    @FXML
    private TextField type;
    @FXML
    private TextField support;
   
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField recherche;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           afficherPublicite();
          search_user();
          // TODO
    }   
    public void afficherPublicite() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Publicite> list =pcr.getPublicite();
        //   System.out.println(pcr.getSujet().toString());
          id_tab.setCellValueFactory(new PropertyValueFactory<Publicite, Integer>("id_offre"));
        //ObservableList<Product> list = FXCollections.observableList(pcd.getProductList());
        type_tab.setCellValueFactory(new PropertyValueFactory<Publicite, String>("type"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        support_tab.setCellValueFactory(new PropertyValueFactory<Publicite, String>("support"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
   

        tab.setItems(list);
        System.out.println("test");
    }
    @FXML
    private void SetValue(MouseEvent event) {
          Publicite selected = tab.getSelectionModel().getSelectedItem();
        if (selected != null) {
            
      
         
          //  String idsujettab = String.valueOf(selected.getId_suj());
            
           // tfprixP.setText(Sprix);
            type.setText(String.valueOf(selected.getType()));
                        
            support.setText(String.valueOf(selected.getSupport()));
            

            ID = selected.getId();
        }
    }

    @FXML
    private void modifierPublicite(ActionEvent event) {
           try {
            String requete = "UPDATE Publicite SET id_offre=?,type=?,support=? WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1, id_offre.getText());
                       pst.setString(2, type.getText());
            pst.setString(3, support.getText());

                     pst.executeUpdate();

            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Publicité!");
              a.setHeaderText(null);
              a.setContentText("Publicité Modifiée !!!");
              a.showAndWait();
            
            //*********
            
         
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          afficherPublicite();
    }

    @FXML
    private void supprimerPublicite(ActionEvent event) {
                  try {
            String requete = "DELETE FROM Publicite WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Publicité!");
              a.setHeaderText(null);
              a.setContentText("Publicité supprimée !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Publicité supprimée!");
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       afficherPublicite();
    }
     void search_user() {          
      id_tab.setCellValueFactory(new PropertyValueFactory<Publicite, Integer>("id_offre"));
      //  idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        type_tab.setCellValueFactory(new PropertyValueFactory<Publicite, String>("type"));
        support_tab.setCellValueFactory(new PropertyValueFactory<Publicite, String>("support"));
             

        ObservableList<Publicite> dataList = pcr.getPublicite();
        tab.setItems(dataList);
        FilteredList<Publicite> filteredData = new FilteredList<>(dataList, b -> true);  
 recherche.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(message -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (String.valueOf(message.getSupport()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
  
        
    
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Publicite> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    } 
}

    

