package jeu_de_la_vie.jeu.cellule_visiteur;


import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.commande.CommandeMeurt;
import jeu_de_la_vie.jeu.commande.CommandeVit;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class VisiteurDayNight extends Visiteur {

    Visiteur visiteur;

    public VisiteurDayNight(JeuDeLaVie jeu) {
        super(jeu);
        visiteur= new VisiteurRuleString(jeu, "B3678/S34678");
    }

    @Override
    public void setJeu(JeuDeLaVie jeu) {
        super.setJeu(jeu);
        visiteur.setJeu(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        visiteur.visiteCelluleVivante(cellule);
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        visiteur.visiteCelluleMorte(cellule);
    }
}
