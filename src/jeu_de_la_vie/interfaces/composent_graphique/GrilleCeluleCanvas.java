package jeu_de_la_vie.interfaces.composent_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;
import java.awt.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class GrilleCeluleCanvas extends Canvas implements Observateur {

    private JeuxDeLaVieFacade jeu;

    private JPanel jeuPanel;

    public GrilleCeluleCanvas(JeuxDeLaVieFacade jeu , JPanel parent) {
        super();
        this.jeu = jeu;
        jeu.addToGameChangeListener(this);
        this.jeuPanel = parent;

        this.setMinimumSize(new Dimension(0, 0));
        jeuPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeCanvas();
            }
        });
    }

    private void resizeCanvas() {
        this.setPreferredSize(new Dimension(jeuPanel.getWidth(), jeuPanel.getHeight()));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (jeu == null || g == null) return;

        int size = 0;
        if (getHeight() > getWidth())
            size = getWidth();
        else
            size = getHeight();

        int cellsize = size/ jeu.getxMax();
        int margin = (size - cellsize*jeu.getxMax())/2;
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if(jeu.getGrilleXY(x,y).estVivante()){
                    g.setColor(new Color(67, 160, 71));
                    g.fillOval(margin+ x*cellsize,y*cellsize,cellsize,cellsize);
                }
            }
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actualiser() {
        repaint();
    }
}
