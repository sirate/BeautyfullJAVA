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
import com.mycompany.myapp.entities.Publicite;
import com.mycompany.myapp.services.serviceCat;
import com.mycompany.myapp.services.serviceOffre;
import com.mycompany.myapp.services.servicePublicite;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class ListPublicite extends Form {
      public  ListPublicite(Form previous) {

      
       setTitle("List Publicite");
         
   
 
        
      
        servicePublicite Pu = new servicePublicite();
        ArrayList<Publicite> list = Pu.getAllPublicite();

        {
           
            for (Publicite p : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
                SpanLabel cat= new SpanLabel("Type :" + p.getType());
                 SpanLabel cat1= new SpanLabel("Support  :" + p.getSupport());
                
               
                     
                       c3.add(cat);
                        c3.add(cat1);
              
             
                       
                        
           System.out.println("");
              
  add(c3);
              
            
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
          
        }
     
    }
    
    
}
