package jeu_de_la_vie.jeu.init_strategy;

import jeu_de_la_vie.jeu.cellule.CelluleGrille;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatVivante;

public class InitStrategyDencity implements InitStrategy {

    static final double DEFAULT_FACTOR = 0.5;

    private final int xMax;
    private final int yMax;

    private final double factor;

    public InitStrategyDencity() {
        this(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_FACTOR);
    }

    public InitStrategyDencity(int xMax, int yMax, double factor) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.factor = factor;
    }

    @Override
    public CelluleGrille initGrid() {
        CelluleGrille grid = new CelluleGrille();
        for (int i = -xMax; i < xMax; i++)
            for (int j = -yMax; j < yMax; j++)
                if (Math.random() < factor)
                    grid.setXY(i, j, new Cellule(i, j, CelluleEtatVivante.getInstance()));
        return grid;
    }
}
