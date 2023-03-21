package jeu_de_la_vie.cellule_visiteur;

import jeu_de_la_vie.Cellule;
import jeu_de_la_vie.JeuDeLaVie;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public abstract class Visiteur {

        protected JeuDeLaVie jeu;

        public Visiteur(JeuDeLaVie jeu) {
            this.jeu = jeu;
            jeu.distribueVisiteur(this);
        }

        public abstract void visiteCelluleVivante(Cellule cellule);

        public abstract void visiteCelluleMorte(Cellule cellule);
}
