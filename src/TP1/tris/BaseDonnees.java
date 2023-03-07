package TP1.tris;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class BaseDonnees {

    StrategieTri strategieTri;

    List<String> donnees;

    public BaseDonnees(StrategieTri tri) {
        this.strategieTri = tri;
        donnees = new LinkedList<>();
        creerDonnees();
    }

    private void creerDonnees() {
        for (int i = 0; i < 100; i++)
            donnees.add("Donnée " + i);
        Collections.shuffle(donnees);
    }

    public void afficheDonneesTriees() {
        for (String donnee : strategieTri.trie(donnees))
            System.out.println(donnee);
    }

}
