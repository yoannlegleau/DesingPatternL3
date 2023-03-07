package TP1.canards;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public class Troupe implements Cancaneur {
    private List<Cancaneur> canards;

    public Troupe() {
        canards = new ArrayList<>();
    }

    @Override
    public void cancaner() {
        for (Cancaneur canard : canards) canard.cancaner();
    }

    public void ajouter(Cancaneur canard) {
        canards.add(canard);
    }

    public void supprimer(Cancaneur canard) {
        canards.remove(canard);
    }

    public Cancaneur getCanards(int n) {
        return canards.get(n);
    }

}
