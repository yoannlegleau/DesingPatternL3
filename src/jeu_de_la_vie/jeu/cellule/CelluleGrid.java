package jeu_de_la_vie.jeu.cellule;

import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatMort;

import java.util.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class CelluleGrid implements Iterable<Cellule> {

    private final Map<Integer, Map<Integer, Cellule>> grille;

    private int maxX , maxY; ;


    public CelluleGrid() {
        maxX = Integer.MAX_VALUE;
        maxY = Integer.MAX_VALUE;
        grille = new HashMap<>();
    }

    public CelluleGrid(int maxX, int maxY) {
        this();
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setXY(int x, int y, Cellule cellule) {
        if (!isInGrid(x, y))
            return;
        grille.computeIfAbsent(x, v -> new HashMap<>());
        grille.get(x).computeIfAbsent(y, v -> cellule);
    }

    /**
     * Renvoie la cellule à la position x,y. Si la cellule n'existe pas, cree une cellule morte.
     * @param x coordonnée x
     * @param y coordonnée y
     * @return la cellule à la position x,y
     */
    public Cellule getXY(int x, int y) {
        if (!isInGrid(x, y))
            return null;
        if (!grille.containsKey(x) || !grille.get(x).containsKey(y))
            setXY(x, y, new Cellule(x,y, CelluleEtatMort.getInstance()));
        return grille.get(x).get(y);
    }

    public List<Cellule> getNeighbours(int x, int y) {
        List<Cellule> neighbours = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (isInGrid(i,j) && !(i == x && j == y))
                    neighbours.add(getXY(i, j));
        return neighbours;
    }

    private boolean isInGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < maxX && y < maxY;
    }


    @Override
    public Iterator<Cellule> iterator() {
        return new  RealCelluleGridIterator(grille);
    }

    /**
     * Iterator parcourant la grille toutes les cellules contenues dans la grille.
     */
    private static class RealCelluleGridIterator implements Iterator<Cellule> {

        private final Iterator<Map<Integer, Cellule>> iteratorX;
        private Iterator<Cellule> iteratorY;

        public RealCelluleGridIterator(Map<Integer, Map<Integer, Cellule>> cellules) {
            iteratorX = cellules.values().iterator();
            if (iteratorX.hasNext()) {
                iteratorY = iteratorX.next().values().iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (iteratorY != null && iteratorY.hasNext()) {
                return true;
            } else if (iteratorX.hasNext()) {
                iteratorY = iteratorX.next().values().iterator();
                return hasNext();
            }
            return false;
        }

        @Override
        public Cellule next() {
            return iteratorY.next();
        }
    }

    /**
     * Iterator parcourant la grille toutes les cellules vivantes et leurs voisines.
     * - Bug : si une cellule est voisine de deux cellules vivantes, elle sera parcourue deux fois. ...
     * - TODO Solution : ne pas faire un iterateur en deux étapes mais un iterateur en une étape en creant une liste de cellules à parcourir dans le constructeur.
     */
        private static class InterestingCelluleGridIterator implements Iterator<Cellule>{

            private final CelluleGrid grid;
            private final Iterator<Cellule> iterator;
            private Iterator<Cellule> neighboursIterator;


            public InterestingCelluleGridIterator(CelluleGrid grid) {
                this.grid = grid;
                iterator = new RealCelluleGridIterator(grid.grille);
            }

            @Override
            public boolean hasNext() {
                if (neighboursIterator != null && neighboursIterator.hasNext()) {
                    return true;
                } else if (iterator.hasNext()) {
                    Cellule currentCellule = iterator.next();
                    neighboursIterator = grid.getNeighbours(currentCellule.getX(), currentCellule.getY()).iterator();
                    return hasNext();
                }
                return false;
            }

            @Override
            public Cellule next() {
                return neighboursIterator.next();
            }


        }
}
