package jeu_de_la_vie.jeu;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public interface Observable {

    void atacheObservateur(Observateur observateur);

    void detacheObservateur(Observateur observateur);

    void notifieObservateurs();

}
