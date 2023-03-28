package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.Observateur;
import jeu_de_la_vie.jeu.cellule_etat.Cellule;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurClassique;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class JeuxDeLaVieFacade {

    private JeuDeLaVie jeu;

    public JeuxDeLaVieFacade() {
        jeu = new JeuDeLaVie();
    }

    public int getxMax() {
        return jeu.getxMax();
    }

    public int getyMax() {
        return jeu.getyMax();
    }

    public Cellule getGrilleXY(int x, int y) {
        return jeu.getGrilleXY(x, y);
    }

    public boolean calculerGenerationSuivante(){
        return jeu.calculerGenerationSuivante();
    }

    public void atacheObservateur(Observateur observateur) {
        jeu.atacheObservateur(observateur);
    }

}
