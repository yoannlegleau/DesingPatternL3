package TP1.canards;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class AdapteurDOie implements Cancaneur {

    Oie oie ;
    public AdapteurDOie(Oie oie) {
        this.oie = oie;
    }

    @Override
    public void cancaner() {
        oie.cacarder();
    }
}
