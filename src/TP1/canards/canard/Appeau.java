package TP1.canards.canard;

import TP1.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

/**
 * Un Appeau peut cancaner meme s'il n'est pas un valid canard
 */
public class Appeau implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("Couincouin");
    }
}
