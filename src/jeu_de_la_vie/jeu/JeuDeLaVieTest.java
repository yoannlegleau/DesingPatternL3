package jeu_de_la_vie.jeu;

import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule.CelluleGrid;
import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatVivante;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
class JeuDeLaVieTest {

    JeuDeLaVie jeuDeLaVie;

    @BeforeEach
    void setUp() {
        jeuDeLaVie = new JeuDeLaVie(new InitStrategy() {
            @Override
            public CelluleGrid initGrid() {
                CelluleGrid celluleGrid = new CelluleGrid(3,3);
                celluleGrid.setXY(1,0,new Cellule(1,0, CelluleEtatVivante.getInstance()));
                celluleGrid.setXY(1,1,new Cellule(1,1, CelluleEtatVivante.getInstance()));
                celluleGrid.setXY(1,2,new Cellule(1,2, CelluleEtatVivante.getInstance()));
                return celluleGrid;
            }
        });

    }

    @Test
    void calculerGenerationSuivante() {
        jeuDeLaVie.calculerGenerationSuivante();
        assertTrue(jeuDeLaVie.getGrille().getXY(0,1).estVivante());
        assertTrue(jeuDeLaVie.getGrille().getXY(1,1).estVivante());
        assertTrue(jeuDeLaVie.getGrille().getXY(2,1).estVivante());
    }
}