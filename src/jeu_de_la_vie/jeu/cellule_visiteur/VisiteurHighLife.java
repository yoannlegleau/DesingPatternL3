package jeu_de_la_vie.jeu.cellule_visiteur;


import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.commande.CommandeMeurt;
import jeu_de_la_vie.jeu.commande.CommandeVit;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class VisiteurHighLife extends Visiteur {
    public VisiteurHighLife(JeuDeLaVie jeu) {
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
        int nombreDeVoisinesVivantes = cellule.nombreDeVoisinesViventes(jeu);
        if (nombreDeVoisinesVivantes == 3 || nombreDeVoisinesVivantes == 6)
            jeu.ajouteCommande(new CommandeVit(cellule));
    }
}
