package jeu_de_la_vie.jeu.builder;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurClassique;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyDencity;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyEmpty;
import jeu_de_la_vie.jeu.observateur.Observateur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class JeuDeLaVieBuilderImpl extends JeuDeLaVieBuilder {

    private final Set<Observateur> listener;

    private InitStrategy initStrategy;
    private Visiteur rules;

    public static JeuDeLaVieBuilder getBuilder() {
        return new JeuDeLaVieBuilderImpl();
    }

    protected JeuDeLaVieBuilderImpl() {
        initStrategy = new InitStrategyEmpty();
        rules = new VisiteurClassique(null);
        listener = new HashSet<>();
    }

    @Override
    public JeuDeLaVieBuilder setInitStrategy(InitStrategy initStrategy) {
        this.initStrategy = initStrategy;
        return this;
    }

    @Override
    public JeuDeLaVieBuilder setRules(Visiteur rules) {
        this.rules = rules;
        return this;
    }

    @Override
    public JeuDeLaVieBuilder setListener(Observateur observateur) {
        listener.add(observateur);
        return this;
    }

    public JeuDeLaVie build() {
        JeuDeLaVie j =  new JeuDeLaVie(initStrategy);
        rules.setJeu(j);
        j.setRule(rules);

        for (Observateur o : listener)
            j.atacheObservateur(o);

        return j;
    }
}
