package jeu_de_la_vie.jeu.cellule;


import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.etat.CelluleEtat;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class Cellule {

    private CelluleEtat etat;

    int x,y ;

    public Cellule(int x, int y, CelluleEtat etat) {
        this.etat = etat;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void vit() {
        this.etat = this.etat.vit();
    }

    public void meurt() {
        this.etat = this.etat.meurt();
    }

    public boolean estVivante() {
        return this.etat.estVivante();
    }

    public int nombreDeVoisinesViventes(JeuDeLaVie jeu){
        return jeu.getGrille().getLiveNeighbours(x,y).size();
    }


    public void accepte(Visiteur visiteur) {
        this.etat.accepte(visiteur,this);
    }

    @Override
    public String toString() {
        return "Cellule{" +
                "etat=" + etat +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}
