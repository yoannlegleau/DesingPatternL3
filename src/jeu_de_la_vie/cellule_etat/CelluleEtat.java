package jeu_de_la_vie.cellule_etat;

import jeu_de_la_vie.Cellule;
import jeu_de_la_vie.cellule_visiteur.Visiteur;

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
