package systhem_fichier.deuxiemme_partie;

import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;

import java.time.LocalDate;
import java.util.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 */
public class Repertoire extends Composant{

    private LinkedList<Composant> composants;

    public Repertoire(String nom, LocalDate dateCreation) {
        super(nom, dateCreation);
        this.composants = new LinkedList<>();
    }

    @Override
    public void acceptVisiteur(ComposantVisiteur v) {
        v.visiter(this);
    }

    @Override
    public LinkedList<Composant> getComposants() {
        return composants;
    }

    @Override
    public Iterator iterator() {
        return new RepertoireIterator(this);
    }

    public void ajouter(Composant composant) {
        composants.add(composant);
    }

    private class RepertoireIterator implements Iterator<Composant> {

        Stack<Composant> pile;

        Composant root;

        public RepertoireIterator(Repertoire r) {
            pile = new Stack<>();
            root = r;
            pile.add(r);
        }
        @Override
        public boolean hasNext() {
            return !pile.isEmpty();
        }

        @Override
        public Composant next() {
            Composant composantCourent = pile.pop();
            for (Iterator<Composant> it = composantCourent.getComposants().descendingIterator(); it.hasNext(); ) {
                Composant c = it.next();
                pile.add(c);
            }
            return composantCourent;
        }
    }
}