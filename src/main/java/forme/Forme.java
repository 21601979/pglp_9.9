package forme;

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
    /**
     * methode qui renvoi l'id d'une forme.
     * @return id de la forme
     */
    String getID();
}
