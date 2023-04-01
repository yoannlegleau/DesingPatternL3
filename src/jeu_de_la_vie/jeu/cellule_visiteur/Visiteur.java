package jeu_de_la_vie.jeu.cellule_visiteur;


import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 */
public abstract class Visiteur {

        protected JeuDeLaVie jeu;

        public Visiteur(JeuDeLaVie jeu) {
            this.jeu = jeu;
        }

        /**
         * Set le jeu dans le patron Builder du jeu de la vie
         * @param jeu le jeu au quel le visiteur vas ajouté les commandes
         */
        public void setJeu(JeuDeLaVie jeu) {
            this.jeu = jeu;
        }

        public abstract void visiteCelluleVivante(Cellule cellule);

        public abstract void visiteCelluleMorte(Cellule cellule);
}
