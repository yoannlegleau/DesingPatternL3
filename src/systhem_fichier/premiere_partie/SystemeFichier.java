package systhem_fichier.premiere_partie;

import java.util.Iterator;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier
 */
public class SystemeFichier implements Iterable<Fichier> {

    private Fichier[] fichiers;

    public void ajouter(Fichier f){
        if ( fichiers == null)
            fichiers = new Fichier[1];
        else{
            Fichier[] newFichiers = new Fichier[(fichiers.length+1)];
            for (int i = 0 ; i < fichiers.length; i++)
                newFichiers[i] = fichiers[i];
            fichiers = newFichiers;
        }
        fichiers[fichiers.length - 1] = f;
    }

    @Override
    public Iterator<Fichier> iterator(){
        return new IterateurSystemeFichier(fichiers);
    }

    public class IterateurSystemeFichier implements Iterator<Fichier> {

        private int index;

        private Fichier[] fichiers;

        public IterateurSystemeFichier( Fichier[] fs ) {
            index = 0;
            fichiers = fs;
        }

        @Override
        public boolean hasNext(){
            return index < fichiers.length;
        }

        @Override
        public Fichier next(){
            return fichiers[index++];
        }

    }

}
