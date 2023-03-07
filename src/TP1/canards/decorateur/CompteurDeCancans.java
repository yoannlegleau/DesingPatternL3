package TP1.canards.decorateur;

import TP1.canards.Cancaneur;
import TP1.canards.canard.Colvert;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class CompteurDeCancans implements Cancaneur {
    private final Cancaneur cancaneur;
    private static int compteur = 0;

    public CompteurDeCancans(Cancaneur cancaneur) {
        this.cancaneur = cancaneur;
    }

    public static int getNbCancans() {
        return compteur;
    }

    @Override
    public void cancaner() {
        cancaneur.cancaner();
        compteur++;
    }
}
