package fr.uvsq._9;
/**
 * class cercle.
 * @author Tanguy
 *
 */
public class Cercle implements Forme {

    /**
     * point centrale du cercle.
     */
    private Point centre;
    /**
     * rayon du cercle.
     */
    private int rayon;
    /**
     * id d'un cercle.
     */
    private String iD;

    /**
     * constructeur de la class cercle.
     * @param midle point du centre
     * @param size rayon du cercle
     */
    public Cercle(final Point midle, final int size, final String name) {
        centre = midle;
        rayon = size;
        iD = name;
    }

    /**
     * methode pour déplacer un triangle.
     * @param depl poit qui permet de déplacer un cercle
     */
    public void deplace(final Point depl) {
        centre = depl;
    }

    /**
     * methode qui renvoi le string décrivant un cercle.
     */
    @Override
    public String toString() {
        return "Cercle " + centre.toString() + " " + rayon;
    }

    public String getID() {
        return iD;
    }

    public Point getCentre() {
        return centre;
    }

    public int getRayon() {
        return rayon;
    }

}
