/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ASMA
 */
public class Offre {
  private int id;
    private String delai;
     private String description;
      private String titre;
            private String image;
       private int taux;

    public Offre(int id, String delai, String description, String titre,String image, int taux) {
        this.id = id;
        this.delai = delai;
        this.description = description;
        this.titre = titre;
                this.image = image;

        this.taux = taux;
    }

    public Offre() {
    }

    public Offre(String delai, String description, String titre,String image, int taux) {
        this.delai = delai;
        this.description = description;
        this.titre = titre;
                this.image = image;
        this.taux = taux;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", delai=" + delai + ", description=" + description + ", titre=" + titre + ", taux=" + taux + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }
    public int getId() {
        return id;
    }

    public String getDelai() {
        return delai;
    }

    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }

    public int getTaux() {
        return taux;
    }
  
}
