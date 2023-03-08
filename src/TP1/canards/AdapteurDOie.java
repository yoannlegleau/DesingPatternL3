package TP1.canards;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

/**
 * Interface AdapteurDOie qui permet d'adapter une Oie en Cancaneur
 */
public class AdapteurDOie implements Cancaneur {

    /**
     * Oie à adapter
     */
    Oie oie ;

    /**
     * Constructeur d'AdapteurDOie
     * @param oie Oie à adapter
     */
    public AdapteurDOie(Oie oie) {
        this.oie = oie;
    }

    /**
     * Fait cancaner en utilisant le comportement de l'Oie
     */
    @Override
    public void cancaner() {
        oie.cacarder();
    }
}
