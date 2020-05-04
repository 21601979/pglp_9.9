package fr.uvsq._9;

public class Cercle implements Forme{

    Point centre;
    int rayon;

    public Cercle(final Point midle, final int size) {
        centre = midle;
        rayon = size;
    }
    
    public void deplace(final Point depl) {
        centre = depl;
    }
    
    @Override
    public String toString() {
        return "Cercle " + centre.toString() + " " + rayon;
    }

}
