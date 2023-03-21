package jeu_de_la_vie.cellule_visiteur;

import jeu_de_la_vie.Cellule;
import jeu_de_la_vie.JeuDeLaVie;
import jeu_de_la_vie.commande.CommandeMeurt;
import jeu_de_la_vie.commande.CommandeVit;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public class VisiteurClassique extends Visiteur {

    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nombreDeVoisinesVivantes = cellule.nombreDeVoisinesViventes(jeu);
        if (nombreDeVoisinesVivantes < 2 || 3 < nombreDeVoisinesVivantes )
            jeu.ajouteCommande(new CommandeMeurt(cellule));
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        if (cellule.nombreDeVoisinesViventes(jeu) == 3)
            jeu.ajouteCommande(new CommandeVit(cellule));
    }
}
