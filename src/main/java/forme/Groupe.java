package forme;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * class groupe.
 * @author Tanguy
 */
public class Groupe implements Forme {
    /**
     * liste de forme.
     */
    private ArrayList<Forme> l = new ArrayList<Forme>();
    /**
     * id d'un groupe de forme.
     */
    private String id;

    /**
     * constructeur d'un groupe de forme.
     * @param name nom groupe
     */
    public Groupe(final String name) {
        id = name;
    }

    /**
     * methode qui déplace un groupe de forme.
     * @param depl point qui sert de référence pour le déplacement
     */
    public void deplace(final Point depl) {
        Iterator<Forme> i = l.iterator();
        while (i.hasNext()) {
            i.next().deplace(depl);
        }
    }

    /**
     * methode qui ajoute une forme dans un groupe.
     * @param f forme a ajouter
     */
    public void add(final Forme f) {
        l.add(f);
    }

    /**
     * methode qui renvoie le string qui représente un groupe de forme.
     */
    @Override
    public String toString() {
        String res = "groupe " + id;
        Iterator<Forme> i = l.iterator();
        while (i.hasNext()) {
            res = res + " " + "( " + i.next().toString()
                    + " )";
        }
        return res;
    }
    /**
     * methode qui renvoie l'iD du groupe.
     * @return id d'un groupe
     */
    public String getID() {
        return id;
    }
    /**
     * methode qui renvoie un itérateur pour un groupe de forme.
     * @return iterateur d'un groupe
     */
    public Iterator<Forme> getIterator() {
        return l.iterator();
    }
}
