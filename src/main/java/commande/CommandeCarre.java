package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Carre;
import forme.Point;
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
     * @param x coordonée x
     * @param y coordonée y
     * @param size taille du carré
     * @param nom nom du carré
     * @param l liste de forme
     */
    public CommandeCarre(final int x, final int y, final int size,
            final String nom, final ListeForme l) {
        obj = new Carre(new Point(x, y), size, nom);
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
