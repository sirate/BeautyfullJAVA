/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceActualite;
import com.mycompany.myapp.services.serviceCat;


/**
 *
 * @author bhk
 */
public class AddActForm extends Form{
    Form current;

    public AddActForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
       setTitle("Add a new categorie ");
        setLayout(BoxLayout.y());
           TextField titre=new TextField("","entrer titre !!");
        titre.setUIID("TextFieldBlack");
        addStringValue("titre",titre);
        
        TextField sous_titre=new TextField("","entrer sous_titre !!");
        sous_titre.setUIID("TextFieldBlack");
        addStringValue("sous_titre",sous_titre);
       
        
           TextField text=new TextField("","entrer description!!");
        text.setUIID("TextFieldBlack");
        addStringValue("description",text);
        
        
           TextField datecrea=new TextField(""," default today !!");
        datecrea.setUIID("TextFieldBlack");
        addStringValue("date",datecrea);
        
           Button btnAjouter=new Button("Ajouter");
        addStringValue("",btnAjouter);
    
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if(titre.getText()==""|| sous_titre.getText()==""|| text.getText()=="" ){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else
                {
                    try {
                        Actualite t = new Actualite(String.valueOf(titre.getText()).toString(),String.valueOf(sous_titre.getText()).toString(),String.valueOf(text.getText()).toString(),String.valueOf(datecrea.getText()).toString()) ;
                        if( ServiceActualite.getInstance().addAct(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        new ListActualite(current).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "categorie must eb a string", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
     private void addStringValue(String s,Component v ) {
       add(BorderLayout.west  (new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
 
      
    }
    
    
}
