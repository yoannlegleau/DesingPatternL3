package systhem_fichier.deuxiemme_partie.visiteur;

import systhem_fichier.deuxiemme_partie.Composant;
import systhem_fichier.deuxiemme_partie.Fichier;
import systhem_fichier.deuxiemme_partie.Repertoire;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier.deuxiemme_partie.visiteur
 */
public class MaximumVisiteur implements ComposantVisiteur {

    Composant olddest;

    @Override
    public void visiter(Repertoire repertoire) {
        //on ne prand pas en compte les repertoires
    }

    @Override
    public void visiter(Fichier fichier) {
        if (olddest == null)
            olddest = fichier;
        else
            if (olddest.getDateCreation().compareTo( fichier.getDateCreation()) > 0)
                olddest = fichier;
    }

    public Composant getOlddest() {
        return olddest;
    }
}
