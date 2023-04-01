package jeu_de_la_vie.jeu.init_strategy;

import jeu_de_la_vie.jeu.cellule.CelluleGrille;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class InitStrategyEmpty implements InitStrategy{
    @Override
    public CelluleGrille initGrid() {
        return new CelluleGrille(DEFAULT_SIZE, DEFAULT_SIZE);
    }
}
