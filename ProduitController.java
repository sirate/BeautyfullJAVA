/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.OffreCRUD;
import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class ProduitController implements Initializable {

    @FXML
    private TableView<Produit> tab;
    @FXML
    private TableColumn<Produit, Integer> id_tab;
    @FXML
    private TableColumn<Produit, String> nom_tab;
    @FXML
    private TableColumn<Produit, Float> prix_tab;
        OffreCRUD pcr = new OffreCRUD();

    @FXML
    private Button offre;
    int ID;
    String NOM;
    Float PRIX;
    @FXML
    private Button afficher;
    @FXML
    private ImageView Fond;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           showAvis();
    }    
  public void showAvis() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Produit> list =pcr.getProduit();
        //   System.out.println(pcr.getSujet().toString());
        //ObservableList<Product> list = FXCollections.observableList(pcd.getProductList());
        id_tab.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
        prix_tab.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
   
        
        tab.setItems(list);
        System.out.println("test");
    }
    @FXML
    private void SetValue(MouseEvent event) {
           Produit selected = tab.getSelectionModel().getSelectedItem();
        if(selected != null){
        ID = selected.getId();
        NOM = selected.getNom();
        PRIX = selected.getPrix();
        }
    }

    @FXML
    private void AjouterOffre(ActionEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Offre.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          OffreController scene2Controller = fxmlLoader.getController();
          scene2Controller.showId(ID,NOM,PRIX);
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));  
          stage.setTitle("Ajouter une offre");
          stage.show();
    }

    @FXML
    private void AfficherOffre(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          ListeOffreController scene2Controller = fxmlLoader.getController();
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));  
          stage.setTitle("List offre");
          stage.show();
    }
    
}
