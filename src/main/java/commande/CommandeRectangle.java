package commande;

import dao.DAO;
import dao.DAOFactory;
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
     * @param r rectangle a créer
     * @param l liste de forme
     */
    public CommandeRectangle(final Rectangle r, final ListeForme l) {
        obj = r;
        lObj = l;
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
