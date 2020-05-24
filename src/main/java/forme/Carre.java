package forme;

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
     * @param name nom du carrée
     */
    public Carre(final Point setp, final int setsize, final String name) {
        p = setp;
        size = setsize;
        iD = name;
    }

    /**
     * methode qui permet le déplacement d'un carrée.
     * @param depl point qui permet le deplacement d'un carré.
     */
    public void deplace(final Point depl) {
        p = new Point(p.getX() + depl.getX(), p.getY() + depl.getY());
    }

    /**
     * metode qui renvoi le string représentant un carrée.
     * @return String qui représente un cercle
     */
    public String toString() {
        return "Carre " + iD + " " + p.toString() + " " + size;
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
