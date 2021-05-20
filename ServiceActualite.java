/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceActualite {
      public ArrayList<Actualite> actualite ;
    
    public static ServiceActualite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceActualite() {
         req = new ConnectionRequest();
    }

    public static ServiceActualite getInstance() {
        if (instance == null) {
            instance = new ServiceActualite();
        }
        return instance;
    }
 public boolean addAct(Actualite t) {
            String url = Statics.BASE_URL + "/actualite/actualiteaddjson/8?id=5&titre="+t.getTitre()+"&sous_titre="+t.getSous_titre()+"&text="+t.getText()+"&dateCreation="+t.getDate();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
    public ArrayList<Actualite> parseProduit(String jsonText){
        try {
           actualite=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               Actualite t = new Actualite();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("titre").toString());
                t.setSous_titre(obj.get("sous_titre").toString());
                t.setText(obj.get("text").toString());
               t.setDate(obj.get("dateCreation").toString());
                //  t.setNom(obj.get("nom").toString());
                   // t.setReference(obj.get("reference").toString());
                    //t.setPrix(Float.parseFloat(obj.get("prix").toString()));
                      //   t.setImage(obj.get("image").toString());
                      //t.setDescription(obj.get("Description").toString());
             
               actualite.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return  actualite;
    }
    
    public ArrayList< Actualite> getAllProduit(){
         String url = Statics.BASE_URL + "/actualite/showactualitemobile/7";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                actualite = parseProduit (new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return actualite ;
    }
    
    public boolean  Delete(int id){
       String url = Statics.BASE_URL + "/actualite/supprimermobile/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
    
}
