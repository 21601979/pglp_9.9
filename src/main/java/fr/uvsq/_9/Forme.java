package fr.uvsq._9;
/**
 * interface forme.
 * @author Tanguy
 *
 */
public interface Forme {
    /**
     * methode abstraite qui permet de déplacer une forme.
     * @param depl point utiliser pour le déplacement
     */
    void deplace(Point depl);
    
    String getID();
}
