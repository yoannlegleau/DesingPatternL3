package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.JeuDeLaVie;
import jeu_de_la_vie.Observateur;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 3/28/2023
 * @pakage jeu_de_la_vie.interfaces
 */
public class InterfaceAWT extends Canvas implements Observateur {

    private static final double FRAME_SIZE_factor = 0.5 ;

    private JeuDeLaVie jeu;



    public InterfaceAWT(JeuDeLaVie jeu) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenSize.getWidth();

        Frame frame = new Frame();
        frame.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                frame.dispose();
            }
        });
        frame.add(this);
        frame.setSize((int) (screenSize.getWidth()*FRAME_SIZE_factor), (int) (screenSize.getHeight()*FRAME_SIZE_factor));
        frame.setTitle("Jeu de la vie");
        frame.setVisible(true);
        this.jeu = jeu;
        jeu.atacheObservateur(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cellsize = getHeight()/ jeu.getxMax();
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if(jeu.getGrilleXY(x,y).estVivante()){
                    g.setColor(Color.BLACK);
                    g.fillOval(x*cellsize,y*cellsize,cellsize,cellsize);
                }
            }
        }
    }

    @Override
    public void actualiser() {
        repaint();
    }
}
