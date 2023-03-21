package systhem_fichier.deuxiemme_partie.visiteur;

import systhem_fichier.deuxiemme_partie.Fichier;
import systhem_fichier.deuxiemme_partie.Repertoire;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier.deuxiemme_partie
 */
public interface ComposantVisiteur {

    void visiter(Repertoire repertoire);

    void visiter(Fichier fichier);

}
