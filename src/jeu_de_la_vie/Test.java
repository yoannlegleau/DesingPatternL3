package jeu_de_la_vie;

import jeu_de_la_vie.interfaces.InterfaceAWT;
import jeu_de_la_vie.interfaces.InterfaceTerminal;

import static java.lang.Thread.sleep;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class Test {
    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        new InterfaceAWT(jeu);
        new InterfaceTerminal(jeu);
        while (jeu.calculerGenerationSuivante()){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
