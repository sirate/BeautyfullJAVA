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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.serviceCat;
import com.mycompany.myapp.services.serviceOffre;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class ListOffre extends Form {
      public  ListOffre(Form previous) {

      
       setTitle("List Offre");
         
   
 
        
      
        serviceOffre of = new serviceOffre();
        ArrayList<Offre> list = of.getAllOffre();

        {
           
            for (Offre o : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
                SpanLabel cat= new SpanLabel("Delai :" + o.getDelai());
                 SpanLabel cat1= new SpanLabel("Description  :" + o.getDescription());
                  SpanLabel cat3= new SpanLabel("Titre :" + o.getTitre());
                  SpanLabel cat2= new SpanLabel("Image :" + o.getImage());
                  SpanLabel cat4= new SpanLabel("Taux:" + o.getTaux());
                 
               
               
                     
                       c3.add(cat);
                        c3.add(cat1);
                         c3.add(cat3);
                          c3.add(cat2);
                        c3.add(cat4);
       
                         Button Delete =new Button("Delete","LoginButton");
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Warning");
                SpanLabel message = new SpanLabel("Are you sure you want to delete your produit?\nThis action once done cannot be reverted!");
                alert.add(message);
                Button ok = new Button("Proceed");
                Button cancel = new Button(new Command("Cancel"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       of.Delete(o.getId());
                     
                        alert.dispose();
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                new ListOffre(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
              
  add(c3);
              
            
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
          
        }
     
    }
    
    
}
