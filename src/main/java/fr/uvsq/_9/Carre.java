package fr.uvsq._9;

/**
 * class carrée.
 * @author Tanguy
 *
 */
public class Carre implements Forme {

    /**
     * point de référence du carrée.
     */
    private Point p;
    /**
     * taille du carrée.
     */
    private int size;
    /**
     * Id d'un carré.
     */
    private String iD;
    /**
     * constructeur de la class carrée.
     * @param setp point de référence du carrée
     * @param setsize taille du carrée
     */
    public Carre(final Point setp, final int setsize, String name) {
        p = setp;
        size = setsize;
        iD = name;
    }

    /**
     * methode qui permet le déplacement d'un carrée.
     * @param depl point qui permet le deplacement d'un carré.
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
    /**
     * methode qui renvoi p.
     * @return p point
     */
    public Point getP() {
        return p;
    }
    /**
     * methode qui renvoi la taille d'un carré.
     * @return size d'un carre
     */
    public int getSize() {
        return size;
    }
    /**
     * methode qui renvoie l'ID d'un carre.
     * @return iD d'un carre
     */
    public String getID() {
        return iD;
    }

}
