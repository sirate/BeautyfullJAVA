/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Services.PubliciteCRUD;
import Services.OffreCRUD;
import entity.Publicite;
import entity.Offre;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 *
 * @author ASUS
 */
public class PubliciteController implements Initializable {
   @FXML
    private Button valider;
    @FXML
    private TextField id;
     @FXML
    private TextField delaidelai;
    @FXML
    private TextField descriptiondescription;
    @FXML
    private TextField titretitre;
    @FXML
    private TextField tauxtaux;
  
    @FXML
    private TextField offre_id; 
    @FXML
    private TextField type;
    @FXML
    private TextField support;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void showId1 (int ID,String DELAI,String DESCRIPTION,String TITRE,int TAUX)
    {
        id.setText(Integer.toString(ID));
        delaidelai.setText(DELAI);
        descriptiondescription.setText(DESCRIPTION);
        titretitre.setText(TITRE);
        tauxtaux.setText(Integer.toString(TAUX));

    }
  

    @FXML
    private void Ajouterpublicite(ActionEvent event) {
           try
        {
        //save Msg in DATA BASE
        String rid = id.getText();
        String rtype = type.getText();
        String rsupport = support.getText();
        System.out.println(id.getText());
        System.out.println(Integer.parseInt(rid));
        Publicite Pu= new Publicite(Integer.parseInt(rid),rtype,rsupport);
        PubliciteCRUD rec= new PubliciteCRUD();
        rec.addPublicite(Pu);
        // Alert
        Alert
                a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Donner une publicité");
        a.setHeaderText(null);
        a.setContentText("Publicité ajoutée !!!");
        a.showAndWait();
        
        //*********
          }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }         
    }
}
    
    

