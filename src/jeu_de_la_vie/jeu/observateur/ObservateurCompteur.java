package jeu_de_la_vie.jeu.observateur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class ObservateurCompteur implements Observateur {

    private int compteur = 0;

    private Observable observable;

    public ObservateurCompteur(Observable observable) {
        this.observable = observable;
        observable.atacheObservateur(this);
    }

    public int getCompteur() {
        return compteur;
    }

    @Override
    public void actualiser() {
        ++compteur;
    }

    public void detache() {
        observable.detacheObservateur(this);
    }
}

