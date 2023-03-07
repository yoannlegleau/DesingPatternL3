package TP1.canards.decorateur;

import TP1.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class Begayeur implements Cancaneur {
    private final Cancaneur cancaneur;

    public Begayeur(Cancaneur cancaneur) {
        this.cancaneur = cancaneur;
    }
    @Override
    public void cancaner() {
        cancaneur.cancaner();
        cancaneur.cancaner();
    }
}
