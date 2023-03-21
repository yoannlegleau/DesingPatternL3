/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package systhem_fichier.troisieme_partie;

/**
 * Classe Test qui permet de tester les différents tris et stratégies
 */
public class Test {

    public static void main(String[] args) {

        BaseDonnees maBase = BaseDonnees.getInstance();

        System.out.println("\nTest TriJava");
        //Tri par default est le TriJava
        maBase.afficheDonneesTriees();

        System.out.println("\nTest TriBulles");
        maBase.setStrategieTri(new TriBulles());
        maBase.afficheDonneesTriees();

        System.out.println("\nTest TriSelection");
        maBase.setStrategieTri(new TriSelection());
        maBase.afficheDonneesTriees();
    }
}
