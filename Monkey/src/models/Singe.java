package models;


import java.util.ArrayList;
import java.util.HashMap;

public class Singe {
    private String nom;

    private HashMap<Type_Tour, ArrayList<String>> liste_de_tours;

    public Singe() {
    }

    public Singe(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<Type_Tour, ArrayList<String>> getListe_de_tours() {
        return liste_de_tours;
    }

    public void setListe_de_tours(HashMap<Type_Tour, ArrayList<String>> liste_de_tours) {
        this.liste_de_tours = liste_de_tours;
    }

    public HashMap<Type_Tour, ArrayList<String>> executer_tours(){
        return liste_de_tours;
    }

    public enum Type_Tour{
        ACROBATIE,
        MUSIQUE
    }
}
