package jeu_de_la_vie.jeu.cellule_visiteur;


import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.commande.CommandeMeurt;
import jeu_de_la_vie.jeu.commande.CommandeVit;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class VisiteurMaze extends Visiteur {

    public VisiteurMaze(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nombreDeVoisinesVivantes = cellule.nombreDeVoisinesViventes(jeu);
        if ( nombreDeVoisinesVivantes == 0 || nombreDeVoisinesVivantes > 5 )
            jeu.ajouteCommande(new CommandeMeurt(cellule));
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        if (cellule.nombreDeVoisinesViventes(jeu) == 3)
            jeu.ajouteCommande(new CommandeVit(cellule));
    }
}
