/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package canards;

/**
 * Un Colvert fait partie de la famille du canard qui peut cancaner
 */
public class Colvert implements Cancaneur {
    @Override
    public void cancaner() {
        System.out.println("coincoin");
    }
}
