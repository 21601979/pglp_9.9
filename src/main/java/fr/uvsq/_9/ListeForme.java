package fr.uvsq._9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

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
     * variable qui marque la fin du programme.
     */
    private int exit = 0;
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
    /**
     * methode qui suprime un élément de la liste.
     * @param iD identifiant de la forme a suprimer
     */
    public void supp(final String iD) {
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getID().equals(iD)) {
                l.remove(i);
            }
        }
    }
    /**
     * methode toString.
     * @return string représentant une liste de forme
     */
    public String toString() {
        ListIterator<Forme> it = l.listIterator();
        String res = "( \n";
        while (it.hasNext()) {
            res = res + it.next().toString() + "\n";
        }
        res = res + " )";
        return res;
    }
    /**
     * methode qui met fin au programme.
     */
    public void exit() {
        exit = 1;
    }
    /**
     * methode qui renvoi la variable exit.
     * @return entier qui corespond exit
     */
    public int getExit() {
        return exit;
    }
    /**
     * methode qui renvoie l'iterateur de la liste de forme.
     * @return iterateur de liste de forme
     */
    public Iterator<Forme> getIterator() {
        System.out.println(l.size());
        return l.iterator();
    }
    /**
     * methode qui remplace un élément de la liste.
     * @param iD identifiant de la forme
     * @param f forme de remplacement
     */
    public void replace(final String iD, final Forme f) {
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getID().equals(iD)) {
                l.remove(i);
                l.add(f);
            }
        }
    }
    /**
     * methode qui renvoi la taille d'une liste de forme.
     * @return taille de la liste
     */
    public int getSize() {
        return l.size();
    }
    /**
     * methode qui renvoi l'élément i d'une liste.
     * @param i indice de l'élément
     * @return forme
     */
    public Forme get(final int i) {
        return l.get(i);
    }
}
