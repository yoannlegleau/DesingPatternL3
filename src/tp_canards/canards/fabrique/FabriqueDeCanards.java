package tp_canards.canards.fabrique;

import tp_canards.canards.AdapteurDOie;
import tp_canards.canards.Cancaneur;
import tp_canards.canards.Oie;
import tp_canards.canards.canard.Appeau;
import tp_canards.canards.canard.CanardEnPlastique;
import tp_canards.canards.canard.Colvert;
import tp_canards.canards.canard.Mandarin;

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