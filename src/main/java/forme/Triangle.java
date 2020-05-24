package forme;

/**
 * class triangle.
 * @author Tanguy
 */
public class Triangle implements Forme {
    /**
     * point 1.
     */
    private Point p1;
    /**
     * point 2.
     */
    private Point p2;
    /**
     * point 3.
     */
    private Point p3;
    /**
     * ID d'un triangle.
     */
    private String iD;

    /**
     * methode qui initialise un triangle.
     * @param setp1 point 1
     * @param setp2 point 2
     * @param setp3 point 3
     * @param name nom de l'objet
     */
    public Triangle(final Point setp1, final Point setp2,
            final Point setp3, final String name) {
        p1 = setp1;
        p2 = setp2;
        p3 = setp3;
        iD = name;
    }

    /**
     * methode qui déplace un triangle.
     * @param depl point qui sert pour le déplacement
     */
    public void deplace(final Point depl) {
       p1 = new Point(p1.getX() + depl.getX(), p1.getY() + depl.getY());
       p2 = new Point(p2.getX() + depl.getX(), p2.getY() + depl.getY());
       p3 = new Point(p3.getX() + depl.getX(), p3.getY() + depl.getY());
    }

    /**
     * methode qui renvoi un String corespondant a un triangle.
     */
    @Override
    public String toString() {
        return "Triangle " + iD + " " + p1.toString()
        + " " + p2.toString() + " " + p3.toString();
    }

    /**
     * methode qui renvoi l'id.
     * @return id du triangle
     */
    public String getID() {
        return iD;
    }
    /**
     * methode qui renvoi le point 1.
     * @return point 1
     */
    public Point getp1() {
        return p1;
    }
    /**
     * methode qui renvoi le point 2.
     * @return point 2
     */
    public Point getp2() {
        return p2;
    }
    /**
     * methode qui renvoie le point 3.
     * @return point 3
     */
    public Point getp3() {
        return p3;
    }

}
