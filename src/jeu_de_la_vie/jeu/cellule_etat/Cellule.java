package jeu_de_la_vie.jeu.cellule_etat;


import jeu_de_la_vie.jeu.JeuDeLaVie;
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
        int nombreDeVoisinesViventes = 0;
        for (int xOfset = -1; xOfset <= 1; xOfset++) {
            for (int yOfset = -1; yOfset <= 1; yOfset++) {
                if (!(xOfset == 0 && yOfset == 0)) {
                    Cellule voisine = jeu.getGrilleXY(x+xOfset,y+yOfset );
                    if(voisine != null && voisine.estVivante())
                        nombreDeVoisinesViventes++;
                }
            }
        }
        return nombreDeVoisinesViventes;
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
