package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Cercle;
import forme.Point;
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
     * @param x coordonée x
     * @param y coordonée y
     * @param rayon rayon du cercle
     * @ nom du cercle
     * @param l liste de forme
     */
    public CommandeCercle(int x, int y, int rayon, String nom, ListeForme l) {
        obj = new Cercle(new Point(x, y), rayon, nom);
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
