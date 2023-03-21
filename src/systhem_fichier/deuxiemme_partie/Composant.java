package systhem_fichier.deuxiemme_partie;

import systhem_fichier.deuxiemme_partie.visiteur.ComposantVisiteur;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier.deuxiemme_partie
 */
public abstract class Composant implements Iterable<Composant> {

    private String nom;
    private LocalDate dateCreation;

    public Composant(String nom) {
        this.nom = nom;
        this.dateCreation = LocalDate.now();;
    }

    public Composant(String nom, LocalDate dateCreation) {
        this(nom);
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public abstract void acceptVisiteur(ComposantVisiteur v);

    public LinkedList<Composant> getComposants() {
        return new LinkedList<>();
    }
    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public Object next() {
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return nom +":["+ dateCreation.toString()+"]";
    }

}
