package TP1.tris;

import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
public interface StrategieTri {
    <T extends Comparable<T>> List<T> trie(List<T> donnees);
}

class TriJava implements StrategieTri {
    @Override
    public <T extends Comparable<T>> List<T> trie(List<T> donnees) {
        donnees.sort(T::compareTo);
        return donnees;
    }
}

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
