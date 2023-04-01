package jeu_de_la_vie.jeu;

import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.cellule.CelluleGrille;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurClassique;
import jeu_de_la_vie.jeu.commande.Commande;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import jeu_de_la_vie.jeu.observateur.Observable;
import jeu_de_la_vie.jeu.observateur.Observateur;

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
    private CelluleGrille grille;
    private Stack<Commande> commandes;

    private Visiteur rule;

    public JeuDeLaVie(InitStrategy initStrategy) {
        observateurs = new ArrayList<>();
        commandes = new Stack<>();
        grille = initStrategy.initGrid();
        rule = new VisiteurClassique(this);//regle par defaut pour la sécuriter
    }

    public void setRule(Visiteur rull) {
        this.rule = rull;
    }

    //Getters et Setters

    public CelluleGrille getGrille() {
        return grille;
    }

    /**
     * Méthodes du de roulement du jeu
     * @return true si la generation suivente posed des modification
     */
    public boolean calculerGenerationSuivante(){
        boolean isModify = false;
        distribueVisiteur(rule);
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

    private void executeCommandes() {
        while (!commandes.isEmpty())
            commandes.pop().executer();
    }

    //Méthodes du pattern Visiteur
    public void distribueVisiteur(Visiteur visiteur) {
        for (Cellule cellule : grille)
            cellule.accepte(visiteur);
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
