/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author ASUS
 */
public class Publicite {

   private int id;
    private   String type;
      private    String support;

    public Publicite() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSupport() {
        return support;
    }

    

    @Override
    public String toString() {
        return "Publicite{" + "id=" + id + ", type=" + type + ", support=" + support + '}';
    }
    
}
