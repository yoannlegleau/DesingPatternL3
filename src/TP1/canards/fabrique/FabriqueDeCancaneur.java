package TP1.canards.fabrique;

import TP1.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public interface FabriqueDeCancaneur {

    public abstract Cancaneur creeAppeau();

    public abstract Cancaneur creerCanardEnPlastique();

    public abstract Cancaneur creerColvert();

    public abstract Cancaneur creerMandarin();

    public abstract Cancaneur creerOie();

}

