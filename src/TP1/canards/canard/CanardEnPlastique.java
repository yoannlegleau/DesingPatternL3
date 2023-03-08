package TP1.canards.canard;

import TP1.canards.Cancaneur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

/**
 * Un CanardEnPlastique peut cancaner meme s'il est en plastique.
 */
public class CanardEnPlastique implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("Couic");
    }
}
