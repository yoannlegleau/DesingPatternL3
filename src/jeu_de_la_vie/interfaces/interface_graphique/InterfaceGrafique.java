package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.Observateur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 3/28/2023
 * @pakage jeu_de_la_vie.interfaces
 */
public class InterfaceGrafique implements Observateur {

    protected JeuDeLaVie jeu;
    protected int generation = 0;

    public InterfaceGrafique(JeuDeLaVie jeu) {
        this.jeu = jeu;
        jeu.atacheObservateur(this);
    }

    @Override
    public void actualiser() {
        generation ++;
    }
}
