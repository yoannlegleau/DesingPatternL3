package TP1.canards;

import TP1.canards.canard.Appeau;
import TP1.canards.canard.CanardEnPlastique;
import TP1.canards.canard.Colvert;
import TP1.canards.canard.Mandarin;
import TP1.canards.decorateur.Begayeur;
import TP1.canards.decorateur.CompteurDeCancans;
import TP1.canards.fabrique.FabriqueDeCanards;
import TP1.canards.fabrique.FabriqueDeCancaneur;
import TP1.canards.fabrique.FabriqueDeComptage;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

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
        troupe.cancaner();

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
