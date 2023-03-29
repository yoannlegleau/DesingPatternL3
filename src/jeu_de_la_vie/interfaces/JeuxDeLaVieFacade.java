package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.JeuDeLaVieManager;
import jeu_de_la_vie.jeu.observateur.Observable;
import jeu_de_la_vie.jeu.observateur.Observateur;
import jeu_de_la_vie.jeu.cellule_etat.Cellule;
import jeu_de_la_vie.jeu.observateur.ObservateurCompteur;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class JeuxDeLaVieFacade {

    private JeuDeLaVie jeu;

    private JeuDeLaVieManager manager;

    private ObservateurCompteur compteur;

    public JeuxDeLaVieFacade() {
        jeu = new JeuDeLaVie();
        compteur = new ObservateurCompteur(jeu);
        manager = new JeuDeLaVieManager(jeu);
    }

    public int getxMax() {
        return jeu.getxMax();
    }

    public int getyMax() {
        return jeu.getyMax();
    }

    public Cellule getGrilleXY(int x, int y) {
        return jeu.getGrilleXY(x, y);
    }

    public boolean calculerGenerationSuivante(){
        return jeu.calculerGenerationSuivante();
    }

    public void addToGameChangeListener(Observateur observateur) {
        jeu.atacheObservateur(observateur);
    }

    public void addToGameRunListener(Observateur observateur) {
        manager.atacheObservateur(observateur);
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

}
