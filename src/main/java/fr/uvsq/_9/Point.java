package fr.uvsq._9;

/**
 * class point.
 * @author Tanguy
 */
public class Point {
    /**
     * coordonée X.
     */
    private int x;
    /**
     * coordonée Y.
     */
    private int y;
    /**
     * constructeur de la class point.
     * @param setx X
     * @param sety Y
     */
    public Point(final int setx, final int sety) {
        x = setx;
        y = sety;
    }
    /**
     * methode qui retourne la coordonée X.
     * @return coordonée X.
     */
    public int getX() {
        return x;
    }
    /**
     * methode qui retourne la coordonée Y.
     * @return coordonée Y
     */
    public int getY() {
       return y;
    }

    /**
     * methode qui renvoi un string décrivant un point.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
