package dao;

import forme.Carre;
import forme.Cercle;
import forme.Groupe;
import forme.Rectangle;
import forme.Triangle;

/**
 * factory de DAO.
 * @author Tanguy
 *
 */
public final class DAOFactory {
    /**
     * constructeur de la class DAO factory.
     */
    private DAOFactory() { }
    /**
     * methode qui renvoi un DAO carree.
     * @return DAOcarre
     */
    public static DAO<Carre> getDAOcarre() {
        return new DAOcarre();
    }
    /**
     * methode qui renvoi un DAO cercle.
     * @return DAOcerlce
     */
    public static DAO<Cercle> getDAOcercle() {
        return new DAOcercle();
    }
    /**
     * methode qui renvoi un DAO rectangle.
     * @return DAOrectangle
     */
    public static DAO<Rectangle> getDAOrectangle() {
        return new DAOrectangle();
    }
    /**
     * methode qui renvoi un DAO triangle.
     * @return DAOtriangle
     */
    public static DAO<Triangle> getDAOtriangle() {
        return new DAOtriangle();
    }
    /**
     * methode qui renvoi un DAO groupe.
     * @return DAOgroupe
     */
    public static DAO<Groupe> getDAOgroupe() {
        return new DAOgroupe();
    }
}
