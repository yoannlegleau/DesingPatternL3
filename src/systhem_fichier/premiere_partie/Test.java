package systhem_fichier.premiere_partie;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 * @pakage systhem_fichier
 */
public class Test {
    public static void main(String[] args){
        SystemeFichier sysFichier = new SystemeFichier();
        sysFichier.ajouter(new Fichier("test1",1,"Test1"));
        sysFichier.ajouter(new Fichier("test2",2,"Test2"));
        sysFichier.ajouter(new Fichier("test3",3,"Test3"));

        Fichier bigest = new Fichier(null,0,null);
        for (Fichier f: sysFichier)
            if (f.compareTo(bigest) > 0)
                bigest = f;
        System.out.println(bigest.toString());
    }
}
