package TP1.canards.fabrique;

import TP1.canards.AdapteurDOie;
import TP1.canards.Cancaneur;
import TP1.canards.Oie;
import TP1.canards.canard.Appeau;
import TP1.canards.canard.CanardEnPlastique;
import TP1.canards.canard.Colvert;
import TP1.canards.canard.Mandarin;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class FabriqueDeCanards implements FabriqueDeCancaneur {

    @Override
    public Cancaneur creeAppeau() {
        return new Appeau();
    }

    @Override
    public Cancaneur creerCanardEnPlastique() {
        return new CanardEnPlastique();
    }

    @Override
    public Cancaneur creerColvert() {
        return new Colvert();
    }

    @Override
    public Cancaneur creerMandarin() {
        return new Mandarin();
    }

    @Override
    public Cancaneur creerOie() {
        return new AdapteurDOie(new Oie());
    }
}