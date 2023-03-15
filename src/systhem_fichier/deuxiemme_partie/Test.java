package systhem_fichier.deuxiemme_partie;

import systhem_fichier.deuxiemme_partie.visiteur.AffichageVisiteur;
import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;
import systhem_fichier.deuxiemme_partie.visiteur.MaximumVisiteur;

import java.time.LocalDate;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier.deuxiemme_partie
 */
public class Test {
    public static void main(String[] args) {

        Repertoire r1 = new Repertoire("R1", LocalDate.of(2020, 3, 15));
        Repertoire r2 = new Repertoire("R2", LocalDate.of(2021, 3, 3));
        Repertoire r3 = new Repertoire("R3", LocalDate.of(2021, 3, 30));
        Fichier f1 = new Fichier("fichier1", LocalDate.of(2020, 3, 23), "Contenu du fichier 1");
        Fichier f2 = new Fichier("fichier2", LocalDate.of(2021, 3, 12), "Fichier 2");
        Fichier f3 = new Fichier("fichier3", LocalDate.of(2020, 8, 3), "Després est très fort");
        Fichier f4 = new Fichier("fichier4", LocalDate.of(2020, 10, 19), "Très fort est Després");
        Fichier f5 = new Fichier("fichier5", LocalDate.of(2021, 3, 22), "etc...");
        r3.ajouter(f4);
        r3.ajouter(f5);
        r2.ajouter(f1);
        r2.ajouter(f2);
        r2.ajouter(r3);
        r1.ajouter(r2);
        r1.ajouter(f3);

        ComposantVisiteur affichageVisiteur = new AffichageVisiteur();
        MaximumVisiteur maximumVisiteur = new MaximumVisiteur();
        for(Composant c: r1) {
            c.acceptVisiteur(maximumVisiteur);
            c.acceptVisiteur(affichageVisiteur);
        }

        System.out.println("Le plus vieux fichier est:"+maximumVisiteur.getOlddest());


    }
}
