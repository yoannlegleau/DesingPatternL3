package systhem_fichier.premiere_partie;

import java.time.LocalDate;

/**
 * @author LeNomDeLEtudiant
 * @version 0.1 : Date : Mon Mar 13 09:17:17 CET 2023
 *
 */

public class Fichier implements Comparable<Fichier> {
    private String nom;
    private int taille;
    private String contenue;
    private LocalDate dateCreation;

    public Fichier(String nom, int taille, String contenue){
        this.nom = nom;
        this.taille = taille;
        this.contenue = contenue;
        this.dateCreation = LocalDate.now();
    }

    public int getTaille(){
        return taille;
    }

    @Override
    public String toString(){
        return nom;
    }

    @Override
    public int compareTo(Fichier o) {
        if (taille > o.getTaille())
            return 1;
        else if (taille == o.getTaille() )
            return 0;
        else
            return -1;
    }
}