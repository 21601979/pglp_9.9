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
     * @param x1 coordonée x du point 1
     * @param x2 coordonée x du point 2
     * @param x3 coordonée x du point 3
     * @param y1 coordonée x du point 1
     * @param y2 coordonée x du point 2
     * @param y3 coordonée x du point 3
     * @param nom nom du triangle
     * @param l liste de forme
     */
    public CommandeTriangle(final int x1, final int y1, final int x2,
            final int y2, final int x3, final int y3, final String nom,
            final ListeForme l) {
        obj = new Triangle(new Point(x1, y1), new Point(x2, y2),
                new Point(x3, y3), nom);
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
