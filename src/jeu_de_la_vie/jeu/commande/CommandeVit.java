package jeu_de_la_vie.jeu.commande;

import jeu_de_la_vie.jeu.cellule.Cellule;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class CommandeVit extends Commande {

    public CommandeVit(Cellule cellule) {
        super(cellule);
    }

    @Override
    public void executer() {
        cellule.vit();
    }

}
