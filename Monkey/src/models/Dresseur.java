package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Dresseur {
    private String nom;
    private Singe singe;

    public Dresseur() {
    }

    public Dresseur(String nom, Singe singe) {
        this.nom = nom;
        this.singe = singe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Singe getSinge() {
        return singe;
    }

    public void setSinge(Singe singe) {
        this.singe = singe;
    }

    public HashMap<Singe.Type_Tour, ArrayList<String>> demander_tours_singe(){
        return singe.executer_tours();
    }
}
