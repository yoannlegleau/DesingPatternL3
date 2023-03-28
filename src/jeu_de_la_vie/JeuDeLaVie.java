package jeu_de_la_vie;

import jeu_de_la_vie.cellule_etat.CelluleEtatMort;
import jeu_de_la_vie.cellule_etat.CelluleEtatVivante;
import jeu_de_la_vie.cellule_visiteur.Visiteur;
import jeu_de_la_vie.cellule_visiteur.VisiteurClassique;
import jeu_de_la_vie.commande.Commande;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LE GLEAU Yoann
 * @version 1, 21/03/2023
 * @pakage bug
 */
public class JeuDeLaVie implements Observable {

    private static final int TAILLE_GRILLE = 10;
    private static final Double INITIALISATION_CELLUTES_VIVENTS_FACTEUR = 0.5;

    private List<Observateur> observateurs;
    private Cellule[][] grille;

    int xMax, yMax;

    private Stack<Commande> commandes;

    public JeuDeLaVie() {
        observateurs = new ArrayList<>();
        commandes = new Stack<Commande>();
        this.xMax = TAILLE_GRILLE;
        this.yMax = TAILLE_GRILLE;
        initialiseGrille();
    }
    private void initialiseGrille() {
        this.grille = new Cellule[xMax][yMax];
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                if (Math.random() < INITIALISATION_CELLUTES_VIVENTS_FACTEUR)
                    this.grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
                else
                    this.grille[i][j] = new Cellule(i, j, CelluleEtatVivante.getInstance());
            }
        }
    }

    //Getters et Setters

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public Cellule getGrilleXY(int x, int y) {
        if (x < 0 || x >= xMax || y < 0 || y >= yMax)
            return null; //en dehors de la grille
        return this.grille[x][y];
    }

    /**
     * Méthodes du de roulement du jeu
     * @return true si la generation suivente posed des modification
     */
    public boolean calculerGenerationSuivante(){
        boolean isModify = false;
        distribueVisiteur(new VisiteurClassique(this));
        if (!commandes.isEmpty())
            isModify = true;
        executeCommandes();
        notifieObservateurs();
        return isModify;
    }


    //Méthodes du pattern Commande

    public void ajouteCommande(Commande commande) {
        commandes.add(commande);
    }

    public void executeCommandes() {
        while (!commandes.isEmpty())
            commandes.pop().executer();
    }


    //Méthodes du pattern Visiteur
    public void distribueVisiteur(Visiteur visiteur) {
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                getGrilleXY(x,y).accepte(visiteur);
            }
        }
    }

    //Méthodes du pattern Observateur

    @Override
    public void atacheObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void detacheObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    @Override
    public void notifieObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.actualiser();
        }
    }

}
