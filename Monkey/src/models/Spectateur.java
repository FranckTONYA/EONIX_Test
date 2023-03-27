package models;

public class Spectateur {
    private String nom;

    public Spectateur() {
    }

    public Spectateur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void applaudit(String nom_tour, String nom_singe){
        System.out.println( nom + " applaudit pendant le tour d'acrobatie ' "
                + nom_tour +" ' du " + nom_singe + ".");
    }

    public void siffle(String nom_tour, String nom_singe){
        System.out.println( nom + " siffle pendant le tour de musique ' "
                + nom_tour +" ' du " + nom_singe + ".");
    }
}
