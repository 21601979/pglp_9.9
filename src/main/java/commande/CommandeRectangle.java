package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Point;
import forme.Rectangle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande pour créer un rectangle.
 * @author Tanguy
 *
 */
public class CommandeRectangle implements Commande {
    /**
     * rectangle a creer.
     */
    private Rectangle obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande rectangle.
     * @param x coordonnée x
     * @param y coordonnée y
     * @param lon longueur du rectangle
     * @param lar largeur du rectangle
     * @param nom nom du rectangle
     * @param l liste de forme
     */
    public CommandeRectangle(final int x, final int y, final int lon,
            final int lar, final String nom, final ListeForme l) {
        obj = new Rectangle(new Point(x, y), lon, lar, nom);
    }
    /**
     * methode qui créer un rectangle.
     */
    public void execute() {
        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }
}
