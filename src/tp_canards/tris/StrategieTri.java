/**
 * @author LE GLEAU Yoann
 * @version 1,07/03/2023
 */


package tp_canards.tris;

import java.util.List;

/**
 * Interface de stratégie de tri
 */
public interface StrategieTri {
    <T extends Comparable<T>> List<T> trie(List<T> donnees);
}

/**
 * Classe de tri utilisant la méthode sort de la classe List de Java (par défaut)
 */
class TriJava implements StrategieTri {
    @Override
    public <T extends Comparable<T>> List<T> trie(List<T> donnees) {
        donnees.sort(T::compareTo);
        return donnees;
    }
}

/**
 * Classe de tri utilisant la méthode de tri à bulles
 * @implNote le tri à bulles est un algorithme de tri qui consiste à comparer deux éléments consécutifs d'une liste et à les échanger si nécessaire.
 */
class TriBulles implements StrategieTri {
    @Override
    public <T extends Comparable<T>> List<T> trie(List<T> donnees) {
        for (int i = 0; i < donnees.size(); i++) {
            for (int j = 0; j < donnees.size() - 1; j++) {
                if (donnees.get(j).compareTo(donnees.get(j + 1)) > 0) {
                    T temp = donnees.get(j);
                    donnees.set(j, donnees.get(j + 1));
                    donnees.set(j + 1, temp);
                }
            }
        }
        return donnees;
    }
}

/**
 * Classe de tri utilisant la méthode de tri par insertion
 * @implNote le tri par insertion est un algorithme de tri qui consiste à prendre le plus petit élément de la liste et à le placer dans la partie triée de la liste.
 */
class TriSelection implements StrategieTri {
    @Override
    public <T extends Comparable<T>> List<T> trie(List<T> donnees) {

        for (int i = 0; i < donnees.size(); i++) {
            int psMin = i;
            for (int j = i + 1; j < donnees.size(); j++) {
                if (donnees.get(j).compareTo(donnees.get(psMin)) < 0) {
                    psMin = j;
                }
            }
            T temp = donnees.get(i);
            donnees.set(i, donnees.get(psMin));
            donnees.set(psMin, temp);
        }
        return donnees;
    }
}
