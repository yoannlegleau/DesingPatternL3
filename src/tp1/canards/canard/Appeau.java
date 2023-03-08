/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp1.canards.canard;

import tp1.canards.Cancaneur;

/**
 * Un Appeau peut cancaner meme s'il n'est pas un valid canard
 */
public class Appeau implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("Couincouin");
    }
}
