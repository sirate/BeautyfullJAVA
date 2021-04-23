/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.OffreCRUD;
import entity.Offre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

  

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class OffreController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private TextField id;
    @FXML
    private TextField Nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField delai;
    @FXML
    private TextArea description;
    @FXML
    private TextField titre;
     @FXML
    private ImageView imageview;
    @FXML
    private TextField taux;
    @FXML
    private Button btnmail;
    @FXML
    private ImageView fond;
    @FXML
    private Button image;
    final FileChooser fileChooser = new FileChooser();
    String imagepath = "null";
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
    }    
 public void showId (int ID,String NOM,Float PRIX)
    {
        id.setText(Integer.toString(ID));
        Nom.setText(NOM);
        prix.setText(Float.toString(PRIX));
    }
    @FXML
    private void Ajouteroffre(ActionEvent event) {
         try
        {
        //save Msg in DATA BASE
              String rid = id.getText();
              String rdelai = delai.getText();
                            String rdescription = description.getText();
              String rtitre = titre.getText();
                            String rimage = image.getText();

              String rtaux = taux.getText();

              System.out.println(id.getText());
              System.out.println(Integer.parseInt(rid));
        Offre Of= new Offre(Integer.parseInt(rid),rdelai,rdescription,rtitre,rimage,Integer.parseInt(rtaux));
        OffreCRUD off= new OffreCRUD();
       off.addOffre(Of);
        // Alert
            Alert
              a = new Alert(Alert.AlertType.WARNING);
              a.setTitle("Donner une offre");
              a.setHeaderText(null);
              a.setContentText("offre ajoutée !!!");
              a.showAndWait();
           
            //*********
           
       
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             //showSujet();
    }

    @FXML
    private void envoyermail(ActionEvent event) { 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/main_1.fxml"));

        try {
            Parent root = loader.load();
            btnmail.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void upload(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagepath = file.toURI().toString();
            Image image = new Image(imagepath);
             imageview.setImage(image); 
        }
    }

  
    }
    

