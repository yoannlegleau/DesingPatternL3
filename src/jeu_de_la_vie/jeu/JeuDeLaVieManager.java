package jeu_de_la_vie.jeu;

import jeu_de_la_vie.jeu.observateur.Observable;
import jeu_de_la_vie.jeu.observateur.Observateur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class JeuDeLaVieManager implements Observable {

    private JeuDeLaVie jeu;

    private boolean running;

    private Thread thread;

    private int deley;

    private List<Observateur> observateurs;

    public JeuDeLaVieManager(JeuDeLaVie jeu) {
        this.jeu = jeu;
        running = false;
        deley = 1000;
        observateurs = new ArrayList<>();
    }

    public void setGenPerSec(double genPerSec ) {
        if (genPerSec == 0) {
            this.deley = Integer.MAX_VALUE;
            return;
        }

        if (deley > 3000){
            this.deley = (int) ( 1000 / genPerSec);
            thread.interrupt();
        }else
            this.deley = (int) ( 1000 / genPerSec);

    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(() -> {
            while (running && jeu.calculerGenerationSuivante()) {
                try {
                    Thread.sleep(deley);
                } catch (InterruptedException ignored) {}
            }
            running = false;
            notifieObservateurs();
        });

        thread.start();
        notifieObservateurs();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        notifieObservateurs();
        try {
            thread.join();
        } catch (InterruptedException ignored) {}
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
        for (Observateur observateur : observateurs)
            observateur.actualiser();
    }
}
