package fr.uvsq._9;

/**
 * class carrée
 * @author Tanguy
 *
 */
public class Carre implements Forme {

    /**
     * point de référence du carrée
     */
    private Point p;
    /**
     * taille du carrée.
     */
    private int size;
    /**
     * Id d'un carré
     */
    private int iD = 0;
    
    public void SetID(int newID) {
        iD = newID;
    }
    /**
     * constructeur de la class carrée.
     * @param setp point de référence du carrée
     * @param setsize taille du carrée
     */
    public Carre(final Point setp, final int setsize) {
        p = setp;
        size = setsize;
    }

    /**
     * methode qui permet le déplacement d'un carrée.
     */
    public void deplace(final Point depl) {
       p = depl;
    }

    /**
     * metode qui renvoi le strijng représentant un carrée.
     */
    @Override
    public String toString() {
        return "Carre " + p.toString() + " " + size;
    }

    public Point getP() {
        return p;
    }

    public int getSize() {
        return size;
    }
    public int getID() {
        return iD;
    }

}
