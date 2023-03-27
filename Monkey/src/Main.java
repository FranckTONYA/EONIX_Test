import models.Dresseur;
import models.Singe;
import models.Spectateur;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Spectateur spectateur = new Spectateur("spectateur");

        // Instantiation et definition des tours du singe 1
        Singe singe1 = new Singe("singe 1");
        ArrayList<String> liste_tours_acrobatie_Singe1 = new ArrayList<>();
        liste_tours_acrobatie_Singe1.add("Marcher sur les mains");
        liste_tours_acrobatie_Singe1.add("Grand écart");
        ArrayList<String> liste_tours_musique_Singe1 = new ArrayList<>();
        liste_tours_musique_Singe1.add("Melodie");
        liste_tours_musique_Singe1.add("Traditionnelle");
        HashMap<Singe.Type_Tour, ArrayList<String>> tours_singe1 = new HashMap<>();
        tours_singe1.put(Singe.Type_Tour.ACROBATIE, liste_tours_acrobatie_Singe1);
        tours_singe1.put(Singe.Type_Tour.MUSIQUE, liste_tours_musique_Singe1);
        singe1.setListe_de_tours(tours_singe1);

        // Instantiation et definition des tours du singe 2
        Singe singe2 = new Singe("singe 2");
        ArrayList<String> liste_tours_acrobatie_Singe2 = new ArrayList<>();
        liste_tours_acrobatie_Singe2.add("Triple roulade avant");
        liste_tours_acrobatie_Singe2.add("Saut en hauteur");
        ArrayList<String> liste_tours_musique_Singe2 = new ArrayList<>();
        liste_tours_musique_Singe2.add("Hip hop");
        HashMap<Singe.Type_Tour, ArrayList<String>> tours_singe2 = new HashMap<>();
        tours_singe2.put(Singe.Type_Tour.ACROBATIE, liste_tours_acrobatie_Singe2);
        tours_singe2.put(Singe.Type_Tour.MUSIQUE, liste_tours_musique_Singe2);
        singe2.setListe_de_tours(tours_singe2);

        // Instantiation des dresseurs
        Dresseur dresseur1 = new Dresseur("dresseur1", singe1);
        Dresseur dresseur2 = new Dresseur("dresseur2", singe2);

        // Demande d'execution des tours de singe par le dresseur 1 et reactions du spectateur par rapport à chaque tour
        executer_tours(dresseur1, spectateur);

        // Demande d'execution des tours de singe par le dresseur 2 et reactions du spectateur par rapport à chaque tour
        executer_tours(dresseur2, spectateur);
    }

    public static void executer_tours(Dresseur dresseur, Spectateur spectateur){
        dresseur.demander_tours_singe().forEach((type_tour, liste_tours) -> {
            if (type_tour.equals(Singe.Type_Tour.ACROBATIE)){
                liste_tours.forEach(nom_tour -> {
                    spectateur.applaudit(nom_tour, dresseur.getSinge().getNom());
                });
            }else if (type_tour.equals(Singe.Type_Tour.MUSIQUE)){
                liste_tours.forEach(nom_tour ->{
                    spectateur.siffle(nom_tour, dresseur.getSinge().getNom());
                } );
            }
        });
    }
}