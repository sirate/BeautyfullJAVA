/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author THINKPAD
 */
public class Avis {
    private int id;
                private int note;

    public int getId() {
        return id;
    }

    public Avis() {
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Avis(int id, int note) {
        this.id = id;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", note=" + note + '}';
    }

    public Avis(int id) {
        this.id = id;
    }
  
    
}
