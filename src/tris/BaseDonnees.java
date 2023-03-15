/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tris;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Une base de données contient une liste de chaînes de caractères allant de 0 à 100
 */
public class BaseDonnees {

    /**
     * Stratégie de tri à utiliser
     */
    StrategieTri strategieTri;

    /**
     * Liste de données sous forme de chaînes de caractères "Donnée 0", "Donnée 1", ...
     */
    List<String> donnees;

    /**
     * Constructeur de la base de données
     * @param tri Stratégie de tri à utiliser
     */
    public BaseDonnees(StrategieTri tri) {
        this.strategieTri = tri;
        donnees = new LinkedList<>();
        creerDonnees();
    }

    /**
     * Crée une liste de données sous forme de chaînes de caractères "Donnée 0", "Donnée 1", ... jusqu'à 100
     */
    private void creerDonnees() {
        for (int i = 0; i < 100; i++)
            donnees.add("Donnée " + i);
        Collections.shuffle(donnees);
    }

    /**
     * Affiche les données triées selon la stratégie de tri
     */
    public void afficheDonneesTriees() {
        for (String donnee : strategieTri.trie(donnees))
            System.out.println(donnee);
    }

}
