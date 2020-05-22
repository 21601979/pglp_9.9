package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Cercle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande cercle.
 * @author Tanguy
 *
 */
public class CommandeCercle implements Commande {
    /**
     * cercle à créer.
     */
    private Cercle obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande pour créer un cercle.
     * @param c cercle a créer
     * @param l liste de forme
     */
    public CommandeCercle(final Cercle c, final ListeForme l) {
        obj = c;
        lObj = l;
    }
    /**
     * methode qui créer un cercle.
     */
    public void execute() {
        DAO<Cercle> d = DAOFactory.getDAOcercle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }
}
