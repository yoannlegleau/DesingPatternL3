package jeu_de_la_vie;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.interfaces.interface_graphique.InterfaceAWT;
import jeu_de_la_vie.interfaces.interface_graphique.InterfaceSwing;
import jeu_de_la_vie.jeu.JeuDeLaVie;

import static java.lang.Thread.sleep;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class Test {
    public static void main(String[] args) {
        JeuxDeLaVieFacade jeu = new JeuxDeLaVieFacade();

        //lancement des interfaces
        //new InterfaceAWT(jeu);
        //new InterfaceTerminal(jeu);
        new InterfaceSwing(jeu);

    }
}
