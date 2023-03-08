/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp1.tris;

/**
 * Classe Test qui permet de tester les différents tris et stratégies
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("\nTest TriJava");
        BaseDonnees maBase = new BaseDonnees(new TriJava());
        maBase.afficheDonneesTriees();

        System.out.println("\nTest TriBulles");
        BaseDonnees maBase2 = new BaseDonnees(new TriBulles());
        maBase2.afficheDonneesTriees();

        System.out.println("\nTest TriSelection");
        BaseDonnees maBase3 = new BaseDonnees(new TriSelection());
        maBase3.afficheDonneesTriees();
    }

}
