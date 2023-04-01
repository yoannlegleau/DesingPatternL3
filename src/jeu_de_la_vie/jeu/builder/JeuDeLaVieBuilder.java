package jeu_de_la_vie.jeu.builder;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule_visiteur.Visiteur;
import jeu_de_la_vie.jeu.init_strategy.InitStrategy;

/**
 * @author LE GLEAU Yoann
 * @version 1, 01/04/2023
 */
public abstract class JeuDeLaVieBuilder {

    private static final JeuDeLaVieBuilder INSTANCE = new JeuDeLaVieBuilderImpl();

    public static JeuDeLaVieBuilder getInstance() {
        return INSTANCE;
    }

    public abstract JeuDeLaVieBuilder setInitStrategy(InitStrategy initStrategy);
    public abstract JeuDeLaVieBuilder setRules(Visiteur rules);
    public abstract JeuDeLaVie build();

}
