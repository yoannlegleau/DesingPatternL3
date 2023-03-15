package systhem_fichier.deuxiemme_partie;

import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;

import java.time.LocalDate;
import java.util.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 */
public class Repertoire extends Composant{

    private List<Composant> composants;

    public Repertoire(String nom, LocalDate dateCreation) {
        super(nom, dateCreation);
        this.composants = new LinkedList<>();
    }

    @Override
    public void acceptVisiteur(ComposantVisiteur v) {
        v.visiter(this);
    }

    public List<Composant> getComposants() {
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

        public RepertoireIterator(Repertoire r) {
            pile = new Stack<>();
            pile.add(r);
        }

        @Override
        public boolean hasNext() {
            return !pile.isEmpty();
        }

        @Override
        public Composant next() {
            Composant composantCourent = pile.pop();
            if (composantCourent.iterator() != null) {
                // la liste tmp et le retournement de la liste sont superflus, car ce n'est toujours pas un réel parcours en profondeur
                List<Composant> tmp = ((Repertoire) composantCourent).getComposants();
                Collections.reverse(tmp);
                for (Composant c: tmp)
                    pile.push(c);
            }
            return composantCourent;
        }
    }
}
