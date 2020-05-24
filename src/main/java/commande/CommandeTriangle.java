package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Point;
import forme.Triangle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande pour créer un triangle.
 * @author Tanguy
 *
 */
public class CommandeTriangle implements Commande {
    /**
     * triangle a creér.
     */
    private Triangle obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande triangle.
     * @param p1 coordonée du point 1
     * @param p2 coordonée point 2
     * @param p3 coordonée point 3
     * @param nom nom du triangle
     * @param l liste de forme
     */
    public CommandeTriangle(final Point p1, final Point p2, final Point p3,
            final String nom, final ListeForme l) {
        obj = new Triangle(p1, p2, p3, nom);
        lObj = l;
    }
    /**
     * methode qui crée un triangle.
     */
    public void execute() {
        DAO<Triangle> d = DAOFactory.getDAOtriangle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }
}
