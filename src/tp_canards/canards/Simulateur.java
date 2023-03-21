/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

package tp_canards.canards;

import tp_canards.canards.decorateur.CompteurDeCancans;
import tp_canards.canards.fabrique.FabriqueDeCanards;
import tp_canards.canards.fabrique.FabriqueDeCancaneur;
import tp_canards.canards.fabrique.FabriqueDeComptage;



/**
 * Classe de test generale pour la partie 1 du TP1
 */
public class Simulateur {

    public void simuler(){
        FabriqueDeCancaneur fabriqueDeCanards = new FabriqueDeCanards();
        FabriqueDeCancaneur fabriqueDeComptage = new FabriqueDeComptage();

        Troupe troupe = new Troupe();

        troupe.ajouter(fabriqueDeComptage.creerColvert());
        troupe.ajouter(fabriqueDeComptage.creerMandarin());
        troupe.ajouter(fabriqueDeComptage.creerCanardEnPlastique());
        troupe.ajouter(fabriqueDeComptage.creeAppeau());
        troupe.ajouter(fabriqueDeCanards.creerOie());

        System.out.println("Simulateur de Canards");
        simuler(troupe);

        System.out.println("Nous avons compté "+CompteurDeCancans.getNbCancans()+" cancans") ;

    }
    public void simuler(Cancaneur c){
        c.cancaner() ;
    }

    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur();
        simulateur.simuler();
    }
}
