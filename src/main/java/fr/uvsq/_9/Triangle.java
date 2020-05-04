package fr.uvsq._9;

public class Triangle implements Forme {

    Point p1;
    Point p2;
    Point p3;
    
    public Triangle(final Point setp1, final Point setp2, final Point setp3) {
        p1 = setp1;
        p2 = setp2;
        p3 = setp3;
    }
    
    public void deplace(final Point depl) {
       int diffx = depl.getX() - p1.getX();
       int diffy = depl.getY() - p1.getY();
       p1 = depl;
       p2 = new Point(p2.getX() + diffx, p2.getY() + diffy);
       p3 = new Point(p3.getX() + diffx, p3.getY() + diffy);
    }
    
    @Override
    public String toString() {
        return "Triangle " + p1.toString() + " " + p2.toString() + " " + p3.toString();
    }

}
