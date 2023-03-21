package jeu_de_la_vie.commande;

import jeu_de_la_vie.Cellule;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public abstract class Commande {

    protected Cellule cellule;

    public Commande(Cellule cellule) {
        this.cellule = cellule;
    }

    public abstract void executer();

}
