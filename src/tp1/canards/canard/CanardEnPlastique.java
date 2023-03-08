/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp1.canards.canard;

import tp1.canards.Cancaneur;

/**
 * Un CanardEnPlastique peut cancaner meme s'il est en plastique.
 */
public class CanardEnPlastique implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("Couic");
    }
}
