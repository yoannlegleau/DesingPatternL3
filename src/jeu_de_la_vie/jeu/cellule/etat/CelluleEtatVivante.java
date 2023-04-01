package jeu_de_la_vie.jeu.cellule.etat;


import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class CelluleEtatVivante implements CelluleEtat {
    private static CelluleEtatVivante instance = null;

    public static CelluleEtatVivante getInstance() {
        if (instance == null)
            instance = new CelluleEtatVivante();
        return instance;
    }

    private CelluleEtatVivante() {}

    @Override
    public CelluleEtat vit() {
        return this;
    }

    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    @Override
    public boolean estVivante() {
        return true;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleVivante(cellule);
    }

}
