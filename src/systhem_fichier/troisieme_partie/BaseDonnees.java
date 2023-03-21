/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package systhem_fichier.troisieme_partie;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Une base de données contient une liste de chaînes de caractères allant de 0 à 100
 */
public class BaseDonnees {

    private static BaseDonnees instance;

    /**
     * Stratégie de tri à utiliser
     */
    private StrategieTri strategieTri;

    /**
     * Liste de données sous forme de chaînes de caractères "Donnée 0", "Donnée 1", ...
     */
    private List<String> donnees;


    public static BaseDonnees getInstance(){
        if (instance == null)
            instance = new BaseDonnees();
        return instance;
    }

    /**
     * Constructeur de la base de données
     */
    private BaseDonnees() {
        this.strategieTri = new TriJava(); //Tri par default
        donnees = new LinkedList<>();

        creerDonnees();
    }

    public void setStrategieTri(StrategieTri strategieTri) {
        this.strategieTri = strategieTri;
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
