package jeu_de_la_vie.jeu.commande;

import jeu_de_la_vie.jeu.cellule_etat.Cellule;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class CommandeMeurt extends Commande {

    public CommandeMeurt(Cellule cellule) {
        super(cellule);
    }

    @Override
    public void executer() {
        cellule.meurt();
    }

}
