package fr.uvsq._9;
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
       int diffx = depl.getX() - p1.getX();
       int diffy = depl.getY() - p1.getY();
       p1 = depl;
       p2 = new Point(p2.getX() + diffx, p2.getY() + diffy);
       p3 = new Point(p3.getX() + diffx, p3.getY() + diffy);
    }

    /**
     * methode qui renvoi un String corespondant a un triangle.
     */
    @Override
    public String toString() {
        return "Triangle " + p1.toString()
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
