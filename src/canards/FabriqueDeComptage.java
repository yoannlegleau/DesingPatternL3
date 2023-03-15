package canards;

public class FabriqueDeComptage implements FabriqueDeCancaneur {

    private static FabriqueDeCancaneur fabriqueDeCancaneur;

    public FabriqueDeComptage() {
        fabriqueDeCancaneur = new  FabriqueDeCanards();
    }

    @Override
    public Cancaneur creeAppeau() {
        return new CompteurDeCancans(fabriqueDeCancaneur.creeAppeau());
    }

    @Override
    public Cancaneur creerCanardEnPlastique() {
        return new CompteurDeCancans(fabriqueDeCancaneur.creerCanardEnPlastique());
    }

    @Override
    public Cancaneur creerColvert() {
        return new CompteurDeCancans(fabriqueDeCancaneur.creerColvert());
    }

    @Override
    public Cancaneur creerMandarin() {
        return new CompteurDeCancans(fabriqueDeCancaneur.creerMandarin());
    }

    @Override
    public Cancaneur creerOie() {
        return new CompteurDeCancans(fabriqueDeCancaneur.creerOie());
    }
}
