/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.serviceCat;
import com.mycompany.myapp.services.ServiceActualite;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListActualite extends Form {
      public  ListActualite(Form previous) {

      
       setTitle("List Actualité");
         
   
 
        
      
        ServiceActualite es = new ServiceActualite();
        ArrayList<Actualite> list = es.getAllProduit();

        {
           
            for (Actualite r : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
                SpanLabel cat= new SpanLabel("Titre :" + r.getTitre());
                 SpanLabel cat1= new SpanLabel("Sous Titre  :" + r.getSous_titre());
                  SpanLabel cat3= new SpanLabel("Description :" + r.getText());
                  SpanLabel cat2= new SpanLabel("Date de creation :" + r.getDate());
              //    SpanLabel cat4= new SpanLabel("description:" + r.getDescription());
                 
               
               
                     
                       c3.add(cat);
                        c3.add(cat1);
                         c3.add(cat3);
                          c3.add(cat2);
                   //     c3.add(cat4);
            Button Delete =new Button("Delete");
                         
                         Font materialFont = FontImage.getMaterialDesignFont();
int size = Display.getInstance().convertToPixels(6, true);
                         materialFont = materialFont.derive(size, Font.BASELINE);
                      FontImage.setMaterialIcon(Delete, FontImage.MATERIAL_DELETE_FOREVER);
    
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
          //  Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Warning");
                SpanLabel message = new SpanLabel("Are you sure you want to delete your Actualite?\nThis action once done cannot be reverted!");
                alert.add(message);
                Button ok = new Button("Proceed");
                Button cancel = new Button(new Command("Cancel"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       es.Delete(r.getId());
                     
                        alert.dispose();
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                new ListActualite(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
              
  add(c3);
              
            
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
          
        }
     
    }
    
}
