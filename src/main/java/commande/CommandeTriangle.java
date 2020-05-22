package commande;

import dao.DAO;
import dao.DAOFactory;
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
     * @param t triangle a créer
     * @param l liste de forme
     */
    public CommandeTriangle(final Triangle t, final ListeForme l) {
        obj = t;
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
