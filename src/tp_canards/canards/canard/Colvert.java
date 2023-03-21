/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp_canards.canards.canard;

import tp_canards.canards.Cancaneur;

/**
 * Un Colvert fait partie de la famille du canard qui peut cancaner
 */
public class Colvert implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("coincoin");
    }
}
