package jeu_de_la_vie.interfaces.composent_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class StartStopButton extends JButton implements Observateur {

    private JeuxDeLaVieFacade jeu;

    public StartStopButton(JeuxDeLaVieFacade jeu) {
        super("▶");
        this.jeu = jeu;
        jeu.atacheObservateur(this);

        addActionListener(e -> action());
    }

    public void action() {
        if (getText().equals("■")) {
            jeu.stop();
        } else {
            jeu.start();
        }
    }


    @Override
    public void actualiser() {
        if (jeu.isRunning()) {
            setText("■");
        } else {
            setText("▶");
        }
    }
}
