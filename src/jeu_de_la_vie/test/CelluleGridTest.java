package jeu_de_la_vie.test;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule.CelluleGrille;
import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatVivante;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
class CelluleGridTest {

    private CelluleGrille celluleGrid;

    @BeforeEach
    void setUp() {
        celluleGrid = new CelluleGrille();
        celluleGrid.setXY(0,0,new Cellule(0,0,CelluleEtatVivante.getInstance()));
        celluleGrid.setXY(1,1,new Cellule(1,1,CelluleEtatVivante.getInstance()));
        celluleGrid.setXY(2,2,new Cellule(2,2,CelluleEtatVivante.getInstance()));

    }

    @Test
    void setXY() {
        Cellule c = new Cellule(0,0,CelluleEtatVivante.getInstance());
        celluleGrid.setXY(0,0,c);
        assertEquals(c,celluleGrid.getXY(0,0));
    }

    @Test
    void getXY() {
        assertTrue(celluleGrid.getXY(0,0).estVivante());
        assertFalse(celluleGrid.getXY(0,1).estVivante());

    }

    @Test
    void iterator() {
        CelluleGrille miniGrid = new CelluleGrille(10,10);
        miniGrid.setXY(2,2,new Cellule(2,2,CelluleEtatVivante.getInstance()));

        for (Cellule c : miniGrid) {
            System.out.println(c.getX()+" "+c.getY());
        }

    }


    @Test
    void getNeighboursVivent() {
        CelluleGrille miniGrid = new CelluleGrille(3,3);
        miniGrid.setXY(1,0,new Cellule(1,0,CelluleEtatVivante.getInstance()));
        miniGrid.setXY(1,1,new Cellule(1,1,CelluleEtatVivante.getInstance()));
        miniGrid.setXY(1,2,new Cellule(1,2,CelluleEtatVivante.getInstance()));

        JeuDeLaVie jeu = new JeuDeLaVie(new InitStrategy() {
            @Override
            public CelluleGrille initGrid() {
                return miniGrid;
            }
        });

        assertEquals(2,miniGrid.getXY(0,0).nombreDeVoisinesViventes(jeu));
        assertEquals(3,miniGrid.getXY(0,1).nombreDeVoisinesViventes(jeu));
        assertEquals(2,miniGrid.getXY(0,2).nombreDeVoisinesViventes(jeu));

        assertEquals(1,miniGrid.getXY(1,0).nombreDeVoisinesViventes(jeu));
        assertEquals(2,miniGrid.getXY(1,1).nombreDeVoisinesViventes(jeu));
        assertEquals(1,miniGrid.getXY(1,2).nombreDeVoisinesViventes(jeu));

        assertEquals(2,miniGrid.getXY(2,0).nombreDeVoisinesViventes(jeu));
        assertEquals(3,miniGrid.getXY(2,1).nombreDeVoisinesViventes(jeu));
        assertEquals(2,miniGrid.getXY(2,2).nombreDeVoisinesViventes(jeu));

    }
}