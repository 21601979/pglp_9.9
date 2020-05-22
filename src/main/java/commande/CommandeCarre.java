package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Carre;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande carré.
 * @author Tanguy
 *
 */
public class CommandeCarre implements Commande {
    /**
     * carre a créer.
     */
    private Carre obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande pour créer un carre.
     * @param c carée à créer
     * @param l liste de forme
     */
    public CommandeCarre(final Carre c, final ListeForme l) {
        obj = c;
        lObj = l;
    }
    /**
     * methode qui créer un carre.
     */
    public void execute() {
        DAO<Carre> d = DAOFactory.getDAOcarre();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }

}
