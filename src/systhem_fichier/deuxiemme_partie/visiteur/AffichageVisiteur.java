package systhem_fichier.deuxiemme_partie.visiteur;

import systhem_fichier.deuxiemme_partie.Composant;
import systhem_fichier.deuxiemme_partie.Fichier;
import systhem_fichier.deuxiemme_partie.Repertoire;
import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier.deuxiemme_partie
 */
public class AffichageVisiteur implements ComposantVisiteur {
    @Override
    public void visiter(Repertoire repertoire) {
        System.out.println(repertoire.getNom()+":");
        for (Composant c: repertoire.getComposants() ) {
            System.out.println("\t- "+c.getNom());
        }
    }

    @Override
    public void visiter(Fichier fichier) {
        System.out.println(fichier.getNom()+":"+ fichier.getContenue());
    }
}
