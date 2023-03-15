package canards;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class FabriqueDeCanards implements FabriqueDeCancaneur {

    @Override
    public Cancaneur creeAppeau() {
        return new Appeau();
    }

    @Override
    public Cancaneur creerCanardEnPlastique() {
        return new CanardEnPlastique();
    }

    @Override
    public Cancaneur creerColvert() {
        return new Colvert();
    }

    @Override
    public Cancaneur creerMandarin() {
        return new Mandarin();
    }

    @Override
    public Cancaneur creerOie() {
        return new AdapteurDOie(new Oie());
    }
}