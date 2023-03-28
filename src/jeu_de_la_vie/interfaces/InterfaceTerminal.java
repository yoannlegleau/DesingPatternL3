package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.JeuDeLaVie;
import jeu_de_la_vie.Observateur;

import java.io.IOException;

import static java.time.InstantSource.system;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class InterfaceTerminal extends InterfaceGrafique  implements Observateur {
    private int generation = 0;

    public InterfaceTerminal(JeuDeLaVie jeu) {
        super(jeu);
    }


    @Override
    public void actualiser() {
        System.out.println();
        System.out.println("Generation : "+ ++generation);
        for (int x = 0; x < jeu.getxMax() ; x++) {
            for (int y = 0; y < jeu.getyMax() ; y++) {
                if(jeu.getGrilleXY(x,y).estVivante())
                    System.out.print("X");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
