package fr.uvsq._9;

public class Carre implements Forme {

    Point p;
    int size;
    
    public Carre(final Point setp, final int setsize) {
        p = setp;
        size = setsize;
    }

    public void deplace(final Point depl) {
       p = depl;
    }
    
    @Override
    public String toString() {
        return "Carre " + p.toString() + " " + size;
    }

}
