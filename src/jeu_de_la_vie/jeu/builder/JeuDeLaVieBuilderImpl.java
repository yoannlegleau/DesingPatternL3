package jeu_de_la_vie.jeu.builder;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;
import jeu_de_la_vie.jeu.cellule_visiteur.VisiteurClassique;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyDencity;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public class JeuDeLaVieBuilderImpl extends JeuDeLaVieBuilder {

    private InitStrategy initStrategy;
    private Visiteur rules;

    public JeuDeLaVieBuilderImpl() {
        initStrategy = new InitStrategyDencity(25,25,0.5);
        rules = new VisiteurClassique(null);
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

    public JeuDeLaVie build() {
        JeuDeLaVie j =  new JeuDeLaVie(initStrategy);
        rules.setJeu(j);
        j.setRule(rules);
        return j;
    }
}
