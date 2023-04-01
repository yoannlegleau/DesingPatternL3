package jeu_de_la_vie.jeu.init_strategy;

import jeu_de_la_vie.jeu.cellule.CelluleGrid;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatVivante;

public class InitStrategyDencity implements InitStrategy {

    private final int xMax;
    private final int yMax;

    private final double factor;

    public InitStrategyDencity(int xMax, int yMax, double factor) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.factor = factor;
    }

    @Override
    public CelluleGrid initGrid() {
        CelluleGrid grid = new CelluleGrid(xMax, yMax);
        for (int i = 0; i < xMax; i++)
            for (int j = 0; j < yMax; j++)
                if (Math.random() < factor)
                    grid.setXY(i, j, new Cellule(i, j, CelluleEtatVivante.getInstance()));
        return grid;
    }
}
