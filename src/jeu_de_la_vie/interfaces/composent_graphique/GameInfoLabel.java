package jeu_de_la_vie.interfaces.composent_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class GameInfoLabel extends JLabel implements Observateur {

    private JeuxDeLaVieFacade jeu;

    public GameInfoLabel(JeuxDeLaVieFacade jeu) {
        super();
        this.jeu = jeu;
        jeu.atacheObservateur(this);
        actualiser();
    }

    @Override
    public void actualiser() {
        setText("Generation : " + jeu.getGeneration());
    }
}
