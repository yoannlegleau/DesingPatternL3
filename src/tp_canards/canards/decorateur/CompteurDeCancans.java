/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp_canards.canards.decorateur;

import tp_canards.canards.Cancaneur;

/**
 * Décorateur de Cancaneur qui compte le nombre de cancans
 */
public class CompteurDeCancans implements Cancaneur {

    /**
     * Cancaneur à décorer
     */
    private final Cancaneur cancaneur;

    /**
     * Compteur de cancans effectués par tous les CompteurDeCancans
     */
    private static int compteur = 0;

    /**
     * Constructeur de CompteurDeCancans
     * @param cancaneur Cancaneur à décorer
     */
    public CompteurDeCancans(Cancaneur cancaneur) {
        this.cancaneur = cancaneur;
    }

    /**
     * Retourne le nombre de cancans effectués par tous les CompteurDeCancans
     * @return Nombre de cancans effectués
     */
    public static int getNbCancans() {
        return compteur;
    }

    /**
     * Fait cancaner le Cancaneur et incrémente le compteur
     */
    @Override
    public void cancaner() {
        cancaneur.cancaner();
        compteur++;
    }
}
