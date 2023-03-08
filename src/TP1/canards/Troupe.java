package TP1.canards;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */

/**
 * Une troupe est un groupe de cancanneur.
 */
public class Troupe implements Cancaneur {
    /**
     * Une liste de canards compose la troupe.
     */
    private List<Cancaneur> canards;

    /**
     * Constructeur de la troupe.
     */
    public Troupe() {
        canards = new ArrayList<>();
    }

    /**
     * Fait cancaner tous les canards de la troupe.
     */
    @Override
    public void cancaner() {
        for (Cancaneur canard : canards) canard.cancaner();
    }

    /**
     * Ajoute un canard à la troupe.
     * @param canard Le canard a ajouté.
     */
    public void ajouter(Cancaneur canard) {
        canards.add(canard);
    }

    /**
     * Supprime un canard de la troupe.
     * @param canard Le canard a supprimé.
     */
    public void supprimer(Cancaneur canard) {
        canards.remove(canard);
    }

    /**
     * Retourne le canard à la position n.
     * @param n La position du canard dans list.
     * @return Le canard à la position n.
     */
    public Cancaneur getCanards(int n) {
        return canards.get(n);
    }

}
