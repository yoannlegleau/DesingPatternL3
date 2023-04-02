package jeu_de_la_vie.jeu.cellule;

import jeu_de_la_vie.jeu.cellule.etat.CelluleEtatMort;

import java.util.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class CelluleGrille implements Iterable<Cellule> {

    private final Map<Integer, Map<Integer, Cellule>> grille;

    private int maxX , maxY; ;


    public CelluleGrille() {
        maxX = Integer.MAX_VALUE;
        maxY = Integer.MAX_VALUE;
        grille = new HashMap<>();
    }

    public CelluleGrille(int maxX, int maxY) {
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
        if (!grille.containsKey(x))
            grille.put(x, new HashMap<>());
        if (!grille.get(x).containsKey(y))
            grille.get(x).put(y, cellule);


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

    public List<Cellule> getSubGridToList(int startX, int startY, int width, int height) {
        List<Cellule> subGrid = new ArrayList<>();
        for (int x = startX; x < startX + width; x++) {
            Map<Integer, Cellule> line;
            if ((line = grille.get(x)) != null)
                for (int y = startY; y < startY + height; y++) {
                    Cellule cellule;
                    if ((cellule = line.get(y)) != null)
                        subGrid.add(cellule);
                }
        }
        return subGrid;
    }

    /**
     * Renvoie la liste des voisins vivants de la cellule à la position x,y.
     * @implNote vue que la methode getXY cree une cellule morte si elle n'existe pas, on ne peut l'utiliser si la cellule est motre car sa genererait recurcivement touts les selule morte.
     * Si la cellule n'a pas de voisins vivants, on la supprime pour ne pas la prendre en compte dans le calcul de la génération suivante.
     * @param x coordonnée x
     * @param y coordonnée y
     * @return la liste des voisins vivants de la cellule à la position x,y
     */
    public List<Cellule> getLiveNeighbours(int x, int y) {
        List<Cellule> neighbours = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (isInGrid(i, j) && !(i == x && j == y)) {
                    if (getXY(x, y).estVivante()) {
                        if (getXY(i, j).estVivante())
                            neighbours.add(getXY(i, j));
                    } else
                        if (grille.containsKey(i) && grille.get(i).containsKey(j) && grille.get(i).get(j).estVivante()) {
                        neighbours.add(getXY(i, j));
                    }
                }



        // Si la cellule n'a pas de voisins vivants, on la supprime pour ne pas la prendre en compte dans le calcul de la génération suivante.
        if (neighbours.isEmpty()) {
            grille.get(x).remove(y);
            if (grille.get(x).isEmpty())
                grille.remove(x);
        } else if (!(grille.get(x).get(y).estVivante()))
            //generer tout les voisin en les chercenant dans la grille
            for (int i = x - 1; i <= x + 1; i++)
                for (int j = y - 1; j <= y + 1; j++)
                    getXY(i, j);


        return neighbours;
    }

    private boolean isInGrid(int x, int y) {
        return x >= -maxX && y >= -maxY && x < maxX && y < maxY;
    }


    @Override
    public Iterator<Cellule> iterator() {
        //return new  RealCelluleGridIterator(grille);
        return dumbIterator();
    }

    // TODO : modifier le vrai iterateur
    private Iterator<Cellule> dumbIterator() {
        List<Cellule> tmp = new LinkedList<>();
        for (Map<Integer, Cellule> map : grille.values())
            for (Cellule cellule : map.values())
                tmp.add(cellule);
        return tmp.iterator();
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

}
