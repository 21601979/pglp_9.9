package forme;

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
     * @param name nom du cercle
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
    /**
     * methode qui renvoie l'id d'un cercle.
     * @return id du cercle
     */
    public String getID() {
        return iD;
    }
    /**
     * methode pour obtenir le point centrale du cercle.
     * @return point centrale du cercle
     */
    public Point getCentre() {
        return centre;
    }
    /**
     * methode pour obtenir le rayon d'un cercle.
     * @return rayon du cercle
     */
    public int getRayon() {
        return rayon;
    }

}
