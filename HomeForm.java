/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Categorie");
        Button btnListTasks = new Button("List categorie");
        Button btnList = new Button("List produit");
        Button btnList1 = new Button("List Offre");
                 Button btnList3 = new Button("List Publicite");

         Button btnList2 = new Button("Add Offre");



            

        btnAddTask.addActionListener(e -> new AddCatForm(current).show());
        btnListTasks.addActionListener(e -> new ListcatForm(current).show());
        btnList.addActionListener(e -> new ListProduit(current).show());
        btnList1.addActionListener(e->new ListOffre(current).show());
       btnList2.addActionListener(e->new AddOffreForm(current).show());
              btnList3.addActionListener(e->new ListPublicite(current).show());


        Container addAll = addAll(btnAddTask, btnListTasks, btnList,btnList1,btnList2,btnList3);

    }

}
