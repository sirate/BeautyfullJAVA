/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.OffreCRUD;
import entity.Offre;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tools.MyConnection;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import com.lowagie.text.Document;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import java.util.List;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author ASMA
 */
public class ListeOffreController implements Initializable {

    @FXML
    private TableView<Offre> tab;
        OffreCRUD pcr = new OffreCRUD();

    @FXML
    private TableColumn<Offre, String> delai_tab;
    @FXML
    private TableColumn<Offre, String> description_tab;
    @FXML
    private TableColumn<Offre, String> titre_tab;
    @FXML
    private TableColumn<Offre, Integer> taux_tab;
     int ID;
     String DELAI;
     String DESCRIPTION;
     String TITRE;
     int TAUX;
     
     
     @FXML
    private TextField delai;
    @FXML
    private TextField description;
    @FXML
    private TextField titre;
    @FXML
    private TextField taux;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField recherche;
            ObservableList<Offre> dataList;

    @FXML
    private Button afficher;
    private Button ajouter;
    @FXML
    private Button bdfpdn;
    @FXML
    private Button ajouterpub;
   /* @FXML
    private Button trie;*/
    @FXML
    private TableColumn<Offre, String> image_tab;
    @FXML
    private Button trie;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficherOffre();
            search_user();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ListeOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  public void afficherOffre() {
        System.out.println("DEBUGG!!!!");
        ObservableList<Offre> list =pcr.getOffre();
        //   System.out.println(pcr.getSujet().toString());
        //ObservableList<Product> list = FXCollections.observableList(pcd.getProductList());

        delai_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("delai"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        description_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
     titre_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("titre"));
             image_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("image"));

             taux_tab.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("taux"));

        tab.setItems(list);
        System.out.println("test");
    }
    @FXML
    private void SetValue(MouseEvent event) {
          Offre selected = tab.getSelectionModel().getSelectedItem();
        if (selected != null) {
            
      
         
          //  String idsujettab = String.valueOf(selected.getId_suj());
            
           // tfprixP.setText(Sprix);
                       ID = selected.getId();
            DELAI=selected.getDelai();
                        DESCRIPTION=selected.getDescription();
            TITRE=selected.getTitre();
            TAUX=selected.getTaux();

        }
    }

    @FXML
    private void modifierOffre(ActionEvent event) {
           try {
            String requete = "UPDATE Offre SET delai=?,description=?,titre=?,taux=? WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1, delai.getText());
                       pst.setString(2, description.getText());
            pst.setString(3, titre.getText());
            pst.setString(4, taux.getText());

                     pst.executeUpdate();

            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Offre!");
              a.setHeaderText(null);
              a.setContentText("Offre Modifiée !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("quantite modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          afficherOffre();
    }

    @FXML
    private void supprimerOffre(ActionEvent event) {
                  try {
            String requete = "DELETE FROM Offre WHERE id="+ID+"";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.executeUpdate();
            // Alert 
            Alert 
              a = new Alert(Alert.AlertType.WARNING); 
              a.setTitle(" Offre!");
              a.setHeaderText(null);
              a.setContentText("Offre supprimé !!!");
              a.showAndWait();
            
            //*********
            
            System.out.println("Offre supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       afficherOffre();
    }
    void search_user() throws SQLException {          
        delai_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("delai"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        description_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
     titre_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("titre"));
                 image_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("image"));

             taux_tab.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("taux"));
        
        dataList =pcr.getOffre();
        tab.setItems(dataList);
        FilteredList<Offre> filteredData = new FilteredList<>(dataList, b -> true);  
 recherche.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(message -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
   if (String.valueOf(message.getDelai()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
    else if (String.valueOf(message.getTaux()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
   
   
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Offre> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }
    /* void search_user() {          
      delai_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("delai"));
      //  idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        description_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
        titre_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("titre"));
                taux_tab.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("taux"));

        ObservableList<Offre> dataList = pcr.getOffre();
        tab.setItems(dataList);
        FilteredList<Offre> filteredData = new FilteredList<>(dataList, b -> true);  
 recherche.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(message -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (String.valueOf(message.getTitre()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
  
        
    
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Offre> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }*/ 

    @FXML
    private void Afficherpublicite(ActionEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListePublicite.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          ListePubliciteController scene2Controller = fxmlLoader.getController();
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));  
          stage.setTitle("List publicité");
          stage.show();
    }

    @FXML
    private void Ajouterpublicite(ActionEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Publicite.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          PubliciteController scene2Controller = fxmlLoader.getController();
          scene2Controller.showId1(ID,DELAI,DESCRIPTION,TITRE,TAUX);
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));  
          stage.setTitle("Ajouter une publicité");
          stage.show();
    }
    
 
    @FXML
    private void genrate_pdf(ActionEvent event) throws FileNotFoundException, DocumentException{
          String file_name="";
        Document doc =new Document();
        
        PdfWriter.getInstance(doc,new FileOutputStream("Les offres.pdf"));
        doc.open();
        //paragraphe
        Paragraph para =new Paragraph("Toutes les offres");
        doc.add(para);
        
        
        PdfPTable tb1=new PdfPTable(4);
        
        tb1.addCell("Titre du produit");
        tb1.addCell("Delai du produit");
        tb1.addCell("Description du produit");
        tb1.addCell("Taux du produit");

        
        for (int i = 0; i < tab.getItems().size(); i++) {
            String Titre = tab.getItems().get(i).getTitre();
            String Delai = tab.getItems().get(i).getDelai();
            String Description = tab.getItems().get(i).getDescription();
             int Taux = tab.getItems().get(i).getTaux();
             String TAUX=String.valueOf(Taux);

            tb1.addCell(Titre);
                        tb1.addCell(Delai);
                                    tb1.addCell(Description);    
                                  tb1.addCell(TAUX);    

        }
        doc.add(tb1);
        doc.close();
                 JOptionPane.showMessageDialog(null, "Fichier PDF enregistrer!");

        
        
    }

  

    @FXML
    private void TrieOffre(ActionEvent event) {
                   OffreCRUD offreC = new OffreCRUD();
        refresh();
        ObservableList<Offre> l = (ObservableList<Offre>) new OffreCRUD ().getAllTrier();
        
       delai_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("delai"));
        //idcreateurtab.setCellValueFactory(new PropertyValueFactory<Sujet, String>("id_createur"));
        description_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
     //   dateposttab.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date_heure_creation"));
     titre_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("titre"));
                 image_tab.setCellValueFactory(new PropertyValueFactory<Offre, String>("image"));

             taux_tab.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("taux"));

        


       

  
        
tab.setItems(l);
    }
    }
     



       
       
    
    
   

