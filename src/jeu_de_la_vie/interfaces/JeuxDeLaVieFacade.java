package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.JeuDeLaVieManager;
import jeu_de_la_vie.jeu.builder.JeuDeLaVieBuilder;
import jeu_de_la_vie.jeu.builder.JeuDeLaVieBuilderImpl;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurClassique;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurHighLife;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import jeu_de_la_vie.jeu.observateur.Observable;
import jeu_de_la_vie.jeu.observateur.Observateur;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.observateur.ObservateurCompteur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class JeuxDeLaVieFacade implements Observable , Observateur {

    private JeuDeLaVie jeu;

    private JeuDeLaVieManager manager;

    private ObservateurCompteur compteur;

    private List<Observateur> observateurs;

    private JeuDeLaVieBuilder builder;

    public JeuxDeLaVieFacade() {
        observateurs = new ArrayList<>();
        builder = JeuDeLaVieBuilderImpl.getInstance();

        JeuDeLaVie jeu = builder.build();
        manager = new JeuDeLaVieManager(jeu);
        manager.atacheObservateur(this);
        compteur = new ObservateurCompteur(jeu);
        initGame(jeu);

    }

    private void initGame(JeuDeLaVie jeuDeLaVie) {
        if (jeu != null)
            jeu.detacheObservateur(this);
        jeu = jeuDeLaVie;
        jeu.atacheObservateur(this);
        manager.setJeu(jeu);

        notifieObservateurs();
    }

    public int getxMax() {
        return jeu.getGrille().getMaxX();
    }

    public int getyMax() {
        return jeu.getGrille().getMaxY();
    }

    public Cellule getGrilleXY(int x, int y) {
        return jeu.getGrille().getXY(x, y);
    }

    public boolean calculerGenerationSuivante(){
        return jeu.calculerGenerationSuivante();
    }

    public int getGeneration() {
        return compteur.getCompteur();
    }

    public void setGenPerSec(double genPerSec ) {
        manager.setGenPerSec(genPerSec);
    }

    public void start() {
        manager.start();
    }

    public void stop() {
        manager.stop();
    }

    public boolean isRunning() {
        return manager.isRunning();
    }

    public void newJeuDeLaVie() {
        initGame(builder.build());
    }

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

    public void setRull(String rullName) {
        switch (rullName){
            case "Clasique (Conway)" -> builder.setRules(new VisiteurClassique(null));
            case "HighLife" -> builder.setRules(new VisiteurHighLife(null));
        }
    }

    public void setInitStrategie(InitStrategy initStrategy) {
        builder.setInitStrategy(initStrategy);
    }

    @Override
    public void actualiser() {
        notifieObservateurs();
    }
}
