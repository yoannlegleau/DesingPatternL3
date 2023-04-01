package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.observateur.Observateur;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 3/28/2023
 * @pakage jeu_de_la_vie.interfaces
 */
public class InterfaceAWT extends InterfaceGrafique implements Observateur {

    private static final double FRAME_SIZE_factor = 0.5 ;

    private Canvas canvas;

    public InterfaceAWT(JeuxDeLaVieFacade jeu) {
        super(jeu);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Frame frame = new Frame();
        frame.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                frame.dispose();
            }
        });
        canvas= new Canvas(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                int cellsize = canvas.getHeight()/ jeu.getxMax();
                int margin = (canvas.getWidth() - cellsize*jeu.getxMax())/2;
                for (int x = 0; x < jeu.getxMax(); x++) {
                    for (int y = 0; y < jeu.getyMax(); y++) {
                        if(jeu.getGrilleXY(x,y).estVivante()){
                            g.setColor(Color.BLACK);
                            g.fillOval(margin+ x*cellsize,y*cellsize,cellsize,cellsize);
                        }
                    }
                }
            }
        };

        frame.add(canvas);
        frame.setSize((int) (screenSize.getHeight()*FRAME_SIZE_factor), (int) (screenSize.getHeight()*FRAME_SIZE_factor));
        frame.setTitle("Jeu de la vie");
        frame.setVisible(true);
        this.jeu = jeu;
        jeu.atacheObservateur(this);
    }


    @Override
    public void actualiser() {
        canvas.repaint();
    }
}
