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
     * constructeur de la class cercle.
     * @param midle point du centre
     * @param size rayon du cercle
     */
    public Cercle(final Point midle, final int size) {
        centre = midle;
        rayon = size;
    }

    /**
     * methode pour déplacer un triangle.
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

}
