/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package canards;

/**
 * Décorateur de Cancaneur qui fait begayer le Cancaneur
 */
public class Begayeur implements Cancaneur {
    /**
     * Cancaneur à décorer
     */
    private final Cancaneur cancaneur;

    /**
     * Constructeur
     * @param cancaneur Cancaneur à décorer
     */
    public Begayeur(Cancaneur cancaneur) {
        this.cancaneur = cancaneur;
    }

    /**
     * Cancane deux fois
     */
    @Override
    public void cancaner() {
        cancaneur.cancaner();
        cancaneur.cancaner();
    }
}
