/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ASUS
 */
public class Publicite {
    private int id;
    private int offre_id;
     private String type;
       private String support;

    public Publicite(int offre_id, String type, String support) {
        this.offre_id = offre_id;
        this.type = type;
        this.support = support;
    }

     

    public Publicite(int id, int offre_id, String type, String support) {
        this.id = id;
        this.offre_id = offre_id;
        this.type = type;
        this.support = support;
    }
  public Publicite() {
      }

    public Publicite( String type, String support) {
         this.offre_id = offre_id;
        this.type = type;
        this.support = support;
    }
    
    public int getId() {
        return id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public String getType() {
        return type;
    }

    public String getSupport() {
        return support;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "Publicite{" + "id=" + id + ", offre_id=" + offre_id + ", type=" + type + ", support=" + support + '}';
    }
}