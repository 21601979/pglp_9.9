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
     * methode qui initialise un triangle.
     * @param setp1 point 1
     * @param setp2 point 2
     * @param setp3 poitn 3
     */
    public Triangle(final Point setp1, final Point setp2, final Point setp3) {
        p1 = setp1;
        p2 = setp2;
        p3 = setp3;
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

}
