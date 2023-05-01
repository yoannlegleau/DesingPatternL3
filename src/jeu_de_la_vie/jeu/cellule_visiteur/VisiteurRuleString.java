package jeu_de_la_vie.jeu.cellule_visiteur;

import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.commande.CommandeMeurt;
import jeu_de_la_vie.jeu.commande.CommandeVit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 30/04/2023
 */
public class VisiteurRuleString extends Visiteur{

    List<Integer> deadRule;
    List<Integer> aliveRule;

    public VisiteurRuleString(JeuDeLaVie jeu, String rule) {
        super(jeu);
        deadRule = new ArrayList<>();
        aliveRule = new ArrayList<>();
        String[] rules = rule.split("/");
        for (String deadRule : rules[0].split(""))
            if (deadRule.matches("[0-9]"))
                this.deadRule.add(Integer.parseInt(deadRule));
        for (String aliveRule : rules[1].split(""))
            if (aliveRule.matches("[0-9]"))
                this.aliveRule.add(Integer.parseInt(aliveRule));
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nombreDeVoisinesVivantes = cellule.nombreDeVoisinesViventes(jeu);
        if (!aliveRule.contains(nombreDeVoisinesVivantes))
            jeu.ajouteCommande(new CommandeMeurt(cellule));

    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        int nombreDeVoisinesVivantes = cellule.nombreDeVoisinesViventes(jeu);
        if (deadRule.contains(nombreDeVoisinesVivantes))
            jeu.ajouteCommande(new CommandeVit(cellule));
    }
}
