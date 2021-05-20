
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.serviceCat;
import com.mycompany.myapp.services.serviceOffre;
import javafx.concurrent.Task;

/**
 *
 * @author bhk
 */
public class AddOffreForm extends Form{

    public AddOffreForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new deal");
        setLayout(BoxLayout.y());
        
        TextField tfDelai = new TextField("","OffreDelai");
                               TextField tfTaux= new TextField("", "OffreTaux");

        TextField tfDescription= new TextField("", "OffreDescription");
                TextField tfTitre= new TextField("", "OffreTitre");



        Button btnValider = new Button("Add Offre");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDelai.getText().length()==0)||(tfTaux.getText().length()==0)||(tfDescription.getText().length()==0)||(tfTitre.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Offre t = new Offre  (tfDelai.getText(), tfDescription.getText(),tfTitre.getText(),(Integer.parseInt(tfTaux.getText())) );
                        if( serviceOffre.getInstance().addOffre(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfDelai,tfDescription,tfTitre,tfTaux, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
