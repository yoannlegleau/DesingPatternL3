package systhem_fichier.deuxiemme_partie;

import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * @author LeNomDeLEtudiant
 * @version 0.1 : Date : Mon Mar 13 09:17:17 CET 2023
 *
 */

public class Fichier extends Composant implements Comparable<Fichier> {
    private int taille;
    private String contenue;

    public Fichier(String nom, LocalDate dateCreation, String contenue) {
        super(nom, dateCreation);
        this.contenue = contenue;
        this.taille = contenue.length();
    }

    public int getTaille(){
        return taille;
    }

    public String getContenue() {
        return contenue;
    }

    @Override
    public void acceptVisiteur(ComposantVisiteur v) {
        v.visiter(this);
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

    @Override
    public Iterator iterator() {
        return null;
    }
}