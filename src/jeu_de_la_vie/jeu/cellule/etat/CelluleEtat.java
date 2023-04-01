package jeu_de_la_vie.jeu.cellule.etat;

import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public interface CelluleEtat {

    public CelluleEtat vit();

    public CelluleEtat meurt();

    public boolean estVivante();

    void accepte(Visiteur visiteur, Cellule cellule);
}
