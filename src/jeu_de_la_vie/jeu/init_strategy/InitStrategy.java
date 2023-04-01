package jeu_de_la_vie.jeu.init_strategy;

import jeu_de_la_vie.jeu.cellule.CelluleGrille;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public interface InitStrategy {

    static final int DEFAULT_SIZE = 25;

    CelluleGrille initGrid();

}

