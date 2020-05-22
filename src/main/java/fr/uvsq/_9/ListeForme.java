package fr.uvsq._9;

import java.util.ArrayList;

import forme.Forme;
/**
 * class qui encapsule une liste de forme.
 * @author Tanguy
 *
 */
public class ListeForme {
    /**
     * liste de forme.
     */
    private ArrayList<Forme> l = new ArrayList<Forme>();
    /**
     * methode qui ajoute une forme a la liste.
     * @param obj forme a ajouter
     */
    public void add(final Forme obj) {
        l.add(obj);
    }
    /**
     * methode qui renvoie une Forme si elle est dans la liste, null sinon.
     * @param iD identifiant de la forme
     * @return Forme
     */
    public Forme search(final String iD) {
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getID().equals(iD)) {
                return l.get(i);
            }
        }
        return null;
    }
}
