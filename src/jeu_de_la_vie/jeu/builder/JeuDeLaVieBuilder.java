package jeu_de_la_vie.jeu.builder;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import jeu_de_la_vie.jeu.observateur.Observateur;

import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public abstract class JeuDeLaVieBuilder {
    protected JeuDeLaVieBuilder() {}
    public abstract JeuDeLaVieBuilder setInitStrategy(InitStrategy initStrategy);
    public abstract JeuDeLaVieBuilder setRules(Visiteur rules);

    public JeuDeLaVieBuilder setListeners(List<Observateur> listener) {
        for (Observateur o : listener)
            setListener(o);
        return this;
    }

    public abstract JeuDeLaVieBuilder setListener(Observateur observateur);
    public abstract JeuDeLaVie build();


}
