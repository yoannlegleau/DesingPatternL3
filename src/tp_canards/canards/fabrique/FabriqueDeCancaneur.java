package tp_canards.canards.fabrique;

import tp_canards.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public interface FabriqueDeCancaneur {

    Cancaneur creeAppeau();

    Cancaneur creerCanardEnPlastique();

    Cancaneur creerColvert();

    Cancaneur creerMandarin();

    Cancaneur creerOie();

}

