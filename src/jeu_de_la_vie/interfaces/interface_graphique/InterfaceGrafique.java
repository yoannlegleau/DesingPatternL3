package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.JeuDeLaVieManager;
import jeu_de_la_vie.jeu.observateur.Observateur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 3/28/2023
 * @pakage jeu_de_la_vie.interfaces
 */
public abstract class InterfaceGrafique {

    protected JeuxDeLaVieFacade jeu;

    public InterfaceGrafique(JeuxDeLaVieFacade jeu) {
        this.jeu = jeu;
    }

}
