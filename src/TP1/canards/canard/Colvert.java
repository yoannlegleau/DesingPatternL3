package TP1.canards.canard;

import TP1.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

/**
 * Un Colvert fait partie de la famille du canard qui peut cancaner
 */
public class Colvert implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("coincoin");
    }
}
