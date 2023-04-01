package jeu_de_la_vie.jeu.cellule.etat;


import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class CelluleEtatMort implements CelluleEtat {

    private static CelluleEtatMort instance = null;

    public static CelluleEtatMort getInstance() {
        if (instance == null)
            instance = new CelluleEtatMort();
        return instance;
    }

    private CelluleEtatMort() {}

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivante.getInstance();
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public boolean estVivante() {
        return false;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleMorte(cellule);
    }

    @Override
    public String toString() {
        return "CelluleEtatMort{}";
    }
}
